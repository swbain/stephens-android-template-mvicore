package com.stephenbain.androidtemplate.home

import com.badoo.mvicore.android.AndroidBindings
import com.badoo.mvicore.binder.using
import com.stephenbain.androidtemplate.common.ViewModelBinder
import javax.inject.Inject

class HomeBinder @Inject constructor(private val feature: HomeFeature) :
    ViewModelBinder<HomeFragment>() {
    override fun bind(view: HomeFragment) {
        HomeBindings(feature, view).setup(view)
    }
}

class HomeBindings(private val feature: HomeFeature, fragment: HomeFragment) :
    AndroidBindings<HomeFragment>(fragment.viewLifecycleOwner) {
    override fun setup(view: HomeFragment) {
        binder.bind(feature to view using HomeUiModelTransformer())
    }
}
