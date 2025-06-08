package rocks.jimi.diordie.manual

fun main() {
    val start = System.nanoTime()
    app().mainService.doSomeWork()
    val end = System.nanoTime()
    println(end - start)
}

fun app() = ManualDiModule


interface ManualDiComponent {
    val mainService: MainService
        get() = MainService(aService, bService)

    val aService: AService
        get() = AService()

    val bService: BService
        get() = BService()

    companion object {
        lateinit var instance: ManualDiComponent
    }
}

object ManualDiModule : ManualDiComponent {
    init {
        ManualDiComponent.instance = this
    }
}
