package com.example.baseapplication.presentation.main.firstView

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.baseapplication.R
import com.example.baseapplication.presentation.base.BaseFragment

class FirstFragment : BaseFragment<FirstViewModel>() {

    override fun getLayoutRes() = R.layout.fragment_first

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}