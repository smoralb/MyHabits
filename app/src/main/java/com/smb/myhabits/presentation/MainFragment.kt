package com.smb.myhabits.presentation

import android.os.Bundle
import android.view.View
import com.smb.core.presentation.base.BaseFragment
import com.smb.myhabits.BR
import com.smb.myhabits.R
import com.smb.myhabits.databinding.FragmentMainBinding
import com.smb.myhabits.presentation.MainState.NavigateToLogin
import com.smb.myhabits.presentation.MainState.NavigateToMain
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainState, FragmentMainBinding, MainViewModel>(
    R.layout.fragment_main, BR.viewModel
) {

    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkUserSession()
    }

    override fun checkViewState(state: MainState) {
        binding.plLandingLoader.visibility = View.GONE
        when (state) {
            is NavigateToMain -> navigateTo(MainFragmentDirections.navigateToHome())
            is NavigateToLogin -> navigateTo(MainFragmentDirections.navigateToLogin())
        }
    }
}