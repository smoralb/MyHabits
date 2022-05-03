package com.example.baseapplication.presentation.main.firstView

import android.os.Bundle
import android.view.View
import com.example.baseapplication.BR
import com.example.baseapplication.R
import com.example.baseapplication.databinding.FragmentFirstBinding
import com.example.baseapplication.presentation.main.firstView.FirstViewState.*
import com.example.baseapplication.presentation.main.firstView.adapter.FirstFragmentAdapter
import com.example.core.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : BaseFragment<FirstViewState, FragmentFirstBinding, FirstViewModel>
    (R.layout.fragment_first, BR.viewModel) {

    override val viewModel by viewModel<FirstViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookList.adapter = FirstFragmentAdapter()
        with(viewModel) {
            initialize()
            viewState.observeForever { checkViewState(it) }
        }
    }

    private fun checkViewState(newState: FirstViewState) {
        when (newState) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
            is NavigateToSecondFragment ->
                navigateTo(FirstFragmentDirections.toSecondFragment(newState.isbn))
        }
    }
}