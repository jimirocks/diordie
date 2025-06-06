package rocks.jimi.diordie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class DiTestSpringApplication

fun main(args: Array<String>) {
    runApplication<DiTestSpringApplication>(*args)
}

// Example of a configuration class with bean definitions
@Configuration
class AppConfig {
    
    @Bean
    fun exampleService(): ExampleService {
        return ExampleServiceImpl()
    }
}

// Example interfaces and implementations to demonstrate wiring
interface ExampleService {
    fun doSomething(): String
}

class ExampleServiceImpl : ExampleService {
    override fun doSomething(): String {
        return "Service is working!"
    }
}

// Example component that uses a wired dependency
@org.springframework.stereotype.Component
class ExampleComponent(private val exampleService: ExampleService) {
    
    fun executeService(): String {
        return "Component using: ${exampleService.doSomething()}"
    }
    
    // This will be called automatically when the application starts
    @org.springframework.context.event.EventListener(org.springframework.boot.context.event.ApplicationStartedEvent::class)
    fun onStart(event: org.springframework.boot.context.event.ApplicationStartedEvent) {
        println(executeService())
        println("App Started in ${event.timeTaken}")
    }
}
