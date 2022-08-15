package com.smb.ft_home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.smb.core.presentation.base.BaseActivity
import com.smb.ft_home.R

class HomeActivity : BaseActivity(
    layoutRes = R.layout.activity_home
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
        fun newIntentFlags(context: Context, flags: Int) =
            Intent(context, HomeActivity::class.java).apply {
                this.flags = flags
            }
    }
}