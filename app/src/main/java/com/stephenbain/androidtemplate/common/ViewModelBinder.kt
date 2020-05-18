package com.stephenbain.androidtemplate.common

import androidx.lifecycle.ViewModel

/**
 * Wrapper used to bind feature to view, and vice versa.
 * Persists across configuration changes.
 */
abstract class ViewModelBinder<T : Any> : ViewModel() {

    abstract fun bind(view: T)
}
