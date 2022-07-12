package com.smb.ft_auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.smb.core.presentation.base.BaseVmActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingActivity : BaseVmActivity<LandingState, LandingViewModel>(R.layout.activity_auth) {

    override val viewModel: LandingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        viewModel.checkUserSession()
    }

    override fun checkViewState(state: LandingState) {
        when (state) {
            is LandingState.NavigateToHome -> viewModel.navigateToHome()
        }
    }re

    companion object {
        fun newIntent(context: Context) = Intent(context, LandingActivity::class.java)
    }
}