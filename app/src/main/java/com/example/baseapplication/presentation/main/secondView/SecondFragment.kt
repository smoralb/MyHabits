package com.example.baseapplication.presentation.main.secondView

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.baseapplication.R
import com.example.baseapplication.presentation.base.BaseFragment

class SecondFragment : BaseFragment<SecondViewModel>() {

    override fun getLayoutRes() = R.layout.fragment_second

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}