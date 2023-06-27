package kz.das.test.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import kz.das.test.presentation.main.MainFragmentViewModel

val mainModule = module {
    viewModelOf(::MainFragmentViewModel)
}