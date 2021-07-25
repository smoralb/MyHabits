package com.example.baseapplication.presentation.core.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("resource")
fun setImageResource(view: ImageView, url: String) {
    view.load(url)
}