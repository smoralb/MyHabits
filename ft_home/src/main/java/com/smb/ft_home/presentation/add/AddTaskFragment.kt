package com.smb.ft_home.presentation.add

import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_home.BR
import com.smb.ft_home.R
import com.smb.ft_home.databinding.FragmentAddBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTaskFragment : BaseFragment<AddTaskState, FragmentAddBinding, AddTaskViewModel>(
    R.layout.fragment_add, BR.viewModel
) {

    override val viewModel: AddTaskViewModel by viewModel()

    override fun checkViewState(state: AddTaskState) {

    }
}