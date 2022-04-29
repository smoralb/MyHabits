package com.example.baseapplication.presentation.main

import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.baseapplication.R
import com.example.baseapplication.presentation.main.firstView.FirstFragment
import com.example.core.presentation.base.BaseActivity

class MainActivity : BaseActivity(
    layoutRes = R.layout.activity_main
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}