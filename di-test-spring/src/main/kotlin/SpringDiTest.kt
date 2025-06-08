package rocks.jimi.diordie.spring

import org.springframework.context.annotation.AnnotationConfigApplicationContext


fun main() {
    val start = System.nanoTime()
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)
    //println("Spring context initialized with beans: ${context.beanDefinitionNames.joinToString(", ")}")
    context.getBean(MainService::class.java).doSomeWork()
    val end = System.nanoTime()
    println(end - start)
}
