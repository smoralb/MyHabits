package com.example.baseapplication.presentation.main.secondView

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.baseapplication.BR
import com.example.baseapplication.R
import com.example.baseapplication.databinding.FragmentSecondBinding
import com.example.core.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : BaseFragment<SecondViewState, FragmentSecondBinding, SecondViewModel>
    (R.layout.fragment_second, BR.viewModel) {

    override val viewModel by viewModel<SecondViewModel>()
    private val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(args.isbn)

        binding.tbHeader.setNavigationOnClickListener {
            navigateTo(SecondFragmentDirections.toFirstFragment())
        }
    }

    override fun checkViewState(state: SecondViewState) {
        // Nothing to do yet
    }
}