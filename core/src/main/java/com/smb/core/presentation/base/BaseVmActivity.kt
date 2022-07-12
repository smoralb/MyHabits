package com.smb.core.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseVmActivity<S : BaseState, out VM : BaseViewModel<S>>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.viewState.observeForever { checkViewState(it) }
    }

    abstract fun checkViewState(state: S)
}