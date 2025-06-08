package rocks.jimi.diordie.koin

class MainService(
    private val aServ: AService,
    private val bServ: BService
) {
    fun doSomeWork() = "Calling A: ${aServ.aFun()} and B: ${bServ.bFun()}"
}

class AService {
    fun aFun() = "aFun"
}

class BService {
    fun bFun() = "bFun"
}
