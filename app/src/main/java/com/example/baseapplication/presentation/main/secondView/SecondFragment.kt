package com.example.baseapplication.presentation.main.secondView

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.baseapplication.R
import com.example.baseapplication.databinding.FragmentSecondBinding
import com.example.baseapplication.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : BaseFragment<FragmentSecondBinding, SecondViewModel>(R.layout.fragment_second) {

    override val viewModel by viewModel<SecondViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}