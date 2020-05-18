package com.stephenbain.androidtemplate.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

abstract class MviFragment<UiModel : Any, UiEvent> : DaggerFragment(), Consumer<UiModel>,
    ObservableSource<UiEvent> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @get:LayoutRes
    protected abstract val layoutId: Int

    private val source = PublishSubject.create<UiEvent>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    protected inline fun <reified T : ViewModelBinder<*>> binder(): Lazy<T> {
        return viewModels { viewModelFactory }
    }

    override fun subscribe(observer: Observer<in UiEvent>) {
        source.subscribe(observer)
    }

    override fun accept(t: UiModel?) {
        t?.let(::render)
    }

    protected abstract fun render(uiModel: UiModel)

    protected fun Observable<out UiEvent>.bindToFeature() {
        subscribe(source)
    }

    protected fun send(uiEvent: UiEvent) {
        source.onNext(uiEvent)
    }
}
