package com.stephenbain.androidtemplate

import com.stephenbain.androidtemplate.common.VmProviderModule
import com.stephenbain.androidtemplate.home.HomeModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        HomeModule::class,
        VmProviderModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MyApplication>
}
