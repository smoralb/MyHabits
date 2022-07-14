package com.smb.ft_main.presentation.home

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_main.BR
import com.smb.ft_main.R
import com.smb.ft_main.databinding.FragmentHomeBinding
import com.smb.ft_main.presentation.home.HomeState.HideLoading
import com.smb.ft_main.presentation.home.HomeState.Loading
import com.smb.ft_main.presentation.home.HomeState.NavigateToSecondFragment
import com.smb.ft_main.presentation.home.HomeState.NavigateUp
import com.smb.ft_main.presentation.home.adapter.HomeFragmentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeState, FragmentHomeBinding, HomeViewModel>
    (R.layout.fragment_home, BR.viewModel) {

    override val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.signOut()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookList.adapter = HomeFragmentAdapter()
        viewModel.initialize()
    }

    override fun checkViewState(state: HomeState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
            is NavigateToSecondFragment ->
                navigateTo(HomeFragmentDirections.toSecondFragment(state.isbn))
            is NavigateUp -> requireActivity().finish()
        }
    }
}