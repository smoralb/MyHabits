package com.smb.ft_home.presentation.add

import android.view.View
import android.widget.Toast
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_home.BR
import com.smb.ft_home.R
import com.smb.ft_home.databinding.FragmentAddBinding
import com.smb.ft_home.presentation.add.AddTaskState.Loading
import com.smb.ft_home.presentation.add.AddTaskState.NavigateUp
import com.smb.ft_home.presentation.add.AddTaskState.ShowError
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTaskFragment : BaseFragment<AddTaskState, FragmentAddBinding, AddTaskViewModel>(
    R.layout.fragment_add, BR.viewModel
) {

    override val viewModel: AddTaskViewModel by viewModel()

    override fun checkViewState(state: AddTaskState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is ShowError -> Toast.makeText(activity, state.message, Toast.LENGTH_SHORT).show()
            is NavigateUp -> navigateUp()
        }
    }
}