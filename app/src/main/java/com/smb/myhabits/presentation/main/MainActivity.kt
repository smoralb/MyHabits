package com.smb.myhabits.presentation.main

import android.os.Bundle
import com.smb.myhabits.R
import com.smb.core.presentation.base.BaseActivity

class MainActivity : BaseActivity(
    layoutRes = R.layout.activity_main
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}