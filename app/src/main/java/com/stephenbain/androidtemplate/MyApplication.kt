package com.stephenbain.androidtemplate

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}
