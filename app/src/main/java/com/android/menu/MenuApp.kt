package com.android.menu

import android.app.Application
import com.android.menu.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class MenuApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(): KoinApplication {
        return startKoin {
            androidContext(this@MenuApp)
            modules(appComponent)
        }
    }
}
