package rocks.jimi.diordie.koin

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Single
import org.koin.core.context.startKoin

// TODO doesn't work with KSP properly
fun main() {
    val start = System.currentTimeMillis()
    startKoin {
        //modules(ExampleModuleAnnotated().module)
    }.koin.get<ExampleService>().doSomething()
    val end = System.currentTimeMillis()
    println("Total time: ${end - start}ms")
}


@Single
class ExampleServiceAnnotated : ExampleService {
    override fun doSomething(): String {
        return "Service is working!"
    }
}

//@Module - claimed to be illegal do not know why
@ComponentScan
class ExampleModuleAnnotated
