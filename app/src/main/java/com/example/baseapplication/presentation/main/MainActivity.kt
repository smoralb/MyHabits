package com.example.baseapplication.presentation.main

import android.os.Bundle
import com.example.baseapplication.R
import com.example.core.presentation.base.BaseActivity

<<<<<<< HEAD
class MainActivity : com.example.core.presentation.base.BaseActivity(hastOptionsMenu = true) {
=======
class MainActivity : BaseActivity(
    layoutRes = R.layout.activity_main,
    hastOptionsMenu = true) {
>>>>>>> develop

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}