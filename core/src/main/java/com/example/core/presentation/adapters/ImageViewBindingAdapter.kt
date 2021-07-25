package com.example.core.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("resource")
fun setImageResource(view: ImageView, url: String) {
    view.load(url)
}