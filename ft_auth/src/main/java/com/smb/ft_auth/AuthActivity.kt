package com.smb.ft_auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.smb.core.presentation.base.BaseActivity

class AuthActivity : BaseActivity(R.layout.activity_auth) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AuthActivity::class.java)
    }
}