package rocks.jimi.diordie.koin

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun main() {
    val start = System.nanoTime()
    startKoin {
        modules(exampleModule)
    }.koin.get<ExampleService>().doSomething()
    val end = System.nanoTime()
    println("Total time: ${end - start} nanoseconds")
}

val exampleModule = module {
    singleOf(::ExampleServiceImpl) { bind<ExampleService>() }
}

interface ExampleService {
    fun doSomething(): String
}

class ExampleServiceImpl : ExampleService {
    override fun doSomething(): String {
        return "Service is working!"
    }
}
