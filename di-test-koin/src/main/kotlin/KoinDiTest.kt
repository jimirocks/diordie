package rocks.jimi.diordie.koin

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun main() {
    val start = System.nanoTime()
    startKoin {
        modules(exampleModule)
    }.koin.get<MainService>().doSomeWork()
    val end = System.nanoTime()
    println(end - start)
}

val exampleModule = module {
    singleOf(::MainService)
    singleOf(::AService)
    singleOf(::BService)
}
