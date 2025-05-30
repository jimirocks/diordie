package rocks.jimi.diordie.di

import org.koin.dsl.module
import rocks.jimi.diordie.repositories.CustomerRepository
import rocks.jimi.diordie.repositories.DeviceRepository
import rocks.jimi.diordie.repositories.InstallationRepository
import rocks.jimi.diordie.services.CustomerService
import rocks.jimi.diordie.services.DeviceService
import rocks.jimi.diordie.services.InstallationService

val appModule = module {
    // Repositories
    single { CustomerRepository() }
    single { InstallationRepository() }
    single { DeviceRepository() }
    
    // Services
    single { CustomerService(get()) }
    single { InstallationService(get(), get()) }
    single { DeviceService(get(), get()) }
}