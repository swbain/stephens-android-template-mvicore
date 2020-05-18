package com.stephenbain.androidtemplate.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.stephenbain.androidtemplate.R
import com.stephenbain.androidtemplate.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    private val viewModel by getViewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: HomeState) = when (state) {
        HomeState.Loading -> showLoading()
        is HomeState.Success -> showHomeText(state.text)
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
