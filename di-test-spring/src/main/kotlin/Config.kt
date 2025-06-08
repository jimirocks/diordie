package rocks.jimi.diordie.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// Example of a configuration class with bean definitions
@Configuration
class AppConfig {

    @Bean
    fun mainService(aService: AService, bService: BService): MainService {
        return MainService(aService, bService)
    }

    @Bean
    fun aService(): AService {
        return AService()
    }

    @Bean
    fun bService(): BService {
        return BService()
    }
}
