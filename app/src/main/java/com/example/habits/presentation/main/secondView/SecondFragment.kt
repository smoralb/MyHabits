package com.example.habits.presentation.main.secondView

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.habits.BR
import com.example.habits.R
import com.example.habits.databinding.FragmentSecondBinding
import com.example.core.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : BaseFragment<SecondViewState, FragmentSecondBinding, SecondViewModel>
    (R.layout.fragment_second, BR.viewModel) {

    override val viewModel by viewModel<SecondViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}