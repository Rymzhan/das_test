package kz.das.test.utils

import android.app.Application
import kz.das.test.di.mainModule
import kz.das.test.di.networkModule
import kz.das.test.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(mainModule, networkModule, repoModule))
        }
    }

}