package kz.das.test.di

import kz.das.test.data.repository.MainRepositoryImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kz.das.test.domain.main.MainRepository

val repoModule = module {
    factoryOf(::MainRepositoryImpl)
        .bind(MainRepository::class)
}
