package com.smb.ft_home.presentation.home

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.recyclerview.widget.ItemTouchHelper
import com.smb.core.presentation.adapters.SwipeControllerActions
import com.smb.core.presentation.adapters.SwipeControllerAlt
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_home.BR
import com.smb.ft_home.R
import com.smb.ft_home.databinding.FragmentHomeBinding
import com.smb.ft_home.presentation.home.HomeState.AddTask
import com.smb.ft_home.presentation.home.HomeState.HideLoading
import com.smb.ft_home.presentation.home.HomeState.Loading
import com.smb.ft_home.presentation.home.HomeState.NavigateToSecondFragment
import com.smb.ft_home.presentation.home.HomeState.NavigateUp
import com.smb.ft_home.presentation.home.adapter.HomeFragmentAdapter
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
        setUpSwipeRecyclerView()
        viewModel.initialize()
    }

    override fun checkViewState(state: HomeState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
            is NavigateToSecondFragment ->
                navigateTo(HomeFragmentDirections.toDetail(state.id))
            is NavigateUp -> requireActivity().finish()
            is AddTask -> navigateTo(HomeFragmentDirections.toAddTask())
        }
    }

    private fun setUpSwipeRecyclerView() {
        binding.srLayout.setOnRefreshListener { viewModel.getTasks() }

        val itemTouchHelper = ItemTouchHelper(object : SwipeControllerAlt(
            object : SwipeControllerActions {
                override fun onRightClicked(itemPosition: Int) {
                    viewModel.deleteTask(itemPosition)
                }

                override fun onLeftClicked(itemPosition: Int) {
                    navigateTo(
                        HomeFragmentDirections.toEdit(viewModel.itemList.value!![itemPosition].id)
                    )
                }
            }, requireContext()
        ) {})
        itemTouchHelper.attachToRecyclerView(binding.rvBookList)
    }
}