package com.stephenbain.androidtemplate.home

import androidx.lifecycle.ViewModel
import com.stephenbain.androidtemplate.common.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [HomeModule.BindViewModel::class])
abstract class HomeModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @Module
    abstract class BindViewModel {
        @Binds
        @IntoMap
        @ViewModelKey(HomeBinder::class)
        abstract fun bindHomeViewModel(binder: HomeBinder): ViewModel
    }
}
