package com.smb.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.smb.components.databinding.MhHeaderBinding

class MHHeader(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private var binding: MhHeaderBinding =
        MhHeaderBinding.inflate(LayoutInflater.from(context), this)

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setNavigationIcon(icon: Int) {
        binding.toolbar.navigationIcon = context.getDrawable(icon)
    }

    fun setNavigationTitle(title: Int) {
        binding.toolbar.title = context.getString(title)
    }

    fun setNavigationIconClickListener(clickListener: () -> Unit) {
        binding.toolbar.setNavigationOnClickListener { clickListener() }
    }

    companion object {

        @JvmStatic
        @BindingAdapter("headerTitle")
        fun setTitle(view: MHHeader, title: Int) {
            view.setNavigationTitle(title)
        }

        @JvmStatic
        @JvmName("setNavigationIcon1")
        @BindingAdapter("headerNavigationIcon")
        fun setNavigationIcon(view: MHHeader, icon: Int) {
            view.setNavigationIcon(icon)
        }

        @JvmStatic
        @BindingAdapter("navigationIconClickListener")
        fun setNavigationIconListener(view: MHHeader, action: () -> Unit) {
            view.setNavigationIconClickListener { action() }
        }
    }
}