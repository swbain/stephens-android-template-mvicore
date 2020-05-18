package com.stephenbain.androidtemplate.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.stephenbain.androidtemplate.R
import com.stephenbain.androidtemplate.common.MviFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : MviFragment<HomeUiModel, HomeUiEvent>() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    private val binder by binder<HomeBinder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.bind(this)
    }

    override fun render(uiModel: HomeUiModel) = when (uiModel) {
        HomeUiModel.Loading -> showLoading()
        is HomeUiModel.Success -> showHomeText(uiModel.text)
    }

    private fun showLoading() {
        text.isVisible = false
        loading.isVisible = true
    }

    private fun showHomeText(homeText: String) {
        text.text = homeText
        text.isVisible = true
        loading.isVisible = false
    }
}

sealed class HomeUiModel {
    object Loading : HomeUiModel()
    data class Success(val text: String) : HomeUiModel()
}

object HomeUiEvent

class HomeUiModelTransformer : (HomeState) -> HomeUiModel {
    override fun invoke(state: HomeState): HomeUiModel {
        return if (state.loading) HomeUiModel.Loading
        else HomeUiModel.Success(state.text)
    }
}
