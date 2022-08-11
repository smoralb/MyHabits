package com.smb.components

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.smb.components.databinding.MhEmptyBinding

class MHEmptyState(context: Context) : FrameLayout(context) {

    val binding: MhEmptyBinding = MhEmptyBinding.inflate(LayoutInflater.from(context), this)

    var title: String = ""
        set(value) {
            if (field != value)
                field = value
        }

    var subTitle: String = ""
        set(value) {
            if (field != value)
                field = value
        }

    companion object {
        @BindingAdapter("setTitle")
        fun setTitle(view: MHEmptyState, title: String) {
            view.title = title
        }

        @BindingAdapter("setSubTitle")
        fun setSubTitle(view: MHEmptyState, subTitle: String) {
            view.subTitle = subTitle
        }
    }

}