package com.example.habits.presentation.main

import android.os.Bundle
import com.example.habits.R
import com.example.core.presentation.base.BaseActivity

class MainActivity : BaseActivity(
    layoutRes = R.layout.activity_main
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}