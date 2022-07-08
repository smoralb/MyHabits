package com.smb.ft_main.presentation.firstView

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_main.R
import com.smb.ft_main.databinding.FragmentFirstBinding
import com.smb.ft_main.presentation.firstView.FirstViewState.*
import com.smb.ft_main.presentation.firstView.adapter.FirstFragmentAdapter
import com.smb.ft_main.BR
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : BaseFragment<FirstViewState, FragmentFirstBinding, FirstViewModel>
    (R.layout.fragment_first, BR.viewModel) {

    override val viewModel by viewModel<FirstViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.signOut()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookList.adapter = FirstFragmentAdapter()
        viewModel.initialize()
    }

    override fun checkViewState(state: FirstViewState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
            is NavigateToSecondFragment ->
                navigateTo(FirstFragmentDirections.toSecondFragment(state.isbn))
            is NavigateUp -> requireActivity().finish()
        }
    }
}