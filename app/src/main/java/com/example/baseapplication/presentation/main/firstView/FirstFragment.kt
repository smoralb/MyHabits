package com.example.baseapplication.presentation.main.firstView

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.baseapplication.BR
import com.example.baseapplication.R
import com.example.baseapplication.databinding.FragmentFirstBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment :
    com.example.core.presentation.base.BaseFragment<FragmentFirstBinding, FirstViewModel>
        (R.layout.fragment_first, BR.viewModel) {

    override val viewModel by viewModel<FirstViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}