package rocks.jimi.diordie

import org.springframework.context.annotation.AnnotationConfigApplicationContext


fun main() {
    val start = System.currentTimeMillis()
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)
    println("Spring context initialized with beans: ${context.beanDefinitionNames.joinToString(", ")}")
    context.getBean(ExampleComponent::class.java).executeService()
    val end = System.currentTimeMillis()
    println("Total time: ${end - start}ms")
}
