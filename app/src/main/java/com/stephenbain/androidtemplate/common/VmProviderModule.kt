package com.stephenbain.androidtemplate.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
object VmProviderModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideViewModelFactory(
        creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val creator = creators[modelClass] ?: creators.entries.firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
            try {
                @Suppress("UNCHECKED_CAST")
                return creator.get() as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}