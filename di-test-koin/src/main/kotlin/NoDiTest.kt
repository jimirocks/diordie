package rocks.jimi.diordie.koin

fun main() {
    val start = System.nanoTime()
    app().exampleService.doSomething()
    val end = System.nanoTime()
    println("Total time: ${end - start} nanoseconds")
}

fun app() = NoDiModule


interface NoDiComponent {
    val exampleService: ExampleService
        get() = ExampleServiceNoDi()

    companion object {
        lateinit var instance: NoDiComponent
    }
}

object NoDiModule : NoDiComponent {
    init {
        NoDiComponent.instance = this
    }
}

class ExampleServiceNoDi : ExampleService {
    override fun doSomething(): String {
        return "Service NODI is working!"
    }
}
