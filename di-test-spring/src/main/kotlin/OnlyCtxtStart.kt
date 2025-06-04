package rocks.jimi.diordie

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("rocks.jimi.diordie")
class AppConfig

fun main() {
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)
    println("Spring context initialized with beans: ${context.beanDefinitionNames.joinToString(", ")}")
}
