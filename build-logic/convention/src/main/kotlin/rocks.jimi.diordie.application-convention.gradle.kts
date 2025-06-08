import java.io.ByteArrayOutputStream

// Apply the application plugin
plugins {
    application
}

// Register the runRepeated task
tasks.register("runRepeated") {
    description = "Runs the application repeatedly and appends output to a file"
    group = "application"
    dependsOn("build")

    // Default values for the task properties
    val cycles = project.findProperty("cycles")?.toString()?.toInt() ?: 5
    val outputFile = project.findProperty("outputFile")?.toString() ?: "${project.layout.buildDirectory.get()}/outputs/repeated-runs.txt"

    doLast {
        // Get the application classpath and main class
        val sourceSets = project.the<SourceSetContainer>()
        val appClasspath = sourceSets.getByName("main").runtimeClasspath
        val application = project.extensions.getByType(org.gradle.api.plugins.JavaApplication::class.java)
        val appMainClass = application.mainClass.get()

        // Create the output directory if it doesn't exist
        val outputDir = project.file(outputFile).parentFile
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }

        // Clear the output file if it exists
        project.file(outputFile).writeText("")

        // Run the application multiple times
        repeat(cycles) { index ->
            val runNumber = index + 1
            println("Running iteration $runNumber of $cycles")

            // Capture the output of the application
            val output = ByteArrayOutputStream()
            project.javaexec {
                classpath = appClasspath
                mainClass.set(appMainClass)
                standardOutput = output
            }

            // Append the output to the file
            project.file(outputFile).appendText(output.toString())
        }

        println("Completed $cycles runs. Output saved to: $outputFile")
    }
}
