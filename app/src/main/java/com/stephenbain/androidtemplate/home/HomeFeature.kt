package com.stephenbain.androidtemplate.home

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeFeature @Inject constructor() :
    ActorReducerFeature<HomeWish, HomeEffect, HomeState, Nothing>(
        initialState = HomeState(loading = true, text = ""),
        actor = HomeActor(),
        reducer = HomeReducer(),
        bootstrapper = HomeBootstrapper()
    ) {

    private class HomeActor : Actor<HomeState, HomeWish, HomeEffect> {
        override fun invoke(state: HomeState, action: HomeWish): Observable<out HomeEffect> {
            return when (action) {
                is HomeWish.ShowText -> Observable.just(HomeEffect.ShowText(action.text))
            }
        }
    }

    private class HomeReducer : Reducer<HomeState, HomeEffect> {
        override fun invoke(state: HomeState, effect: HomeEffect): HomeState {
            return when (effect) {
                is HomeEffect.ShowText -> state.copy(
                    loading = false,
                    text = effect.text
                )
            }
        }
    }

    private class HomeBootstrapper : Bootstrapper<HomeWish> {
        override fun invoke(): Observable<HomeWish> {
            val wish: HomeWish = HomeWish.ShowText("hehe this is ur app lol")
            return Observable.just(wish)
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

data class HomeState(val loading: Boolean, val text: String)

sealed class HomeWish {
    data class ShowText(val text: String) : HomeWish()
}

sealed class HomeEffect {
    data class ShowText(val text: String) : HomeEffect()
}
