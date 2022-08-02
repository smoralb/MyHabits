package com.smb.ft_home.presentation.edit

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.navigation.fragment.navArgs
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_home.BR
import com.smb.ft_home.R
import com.smb.ft_home.databinding.FragmentEditBinding
import com.smb.ft_home.presentation.detail.TaskDetailFragmentArgs
import com.smb.ft_home.presentation.edit.EditTaskState.Loading
import com.smb.ft_home.presentation.edit.EditTaskState.NavigateUp
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTaskFragment : BaseFragment<EditTaskState, FragmentEditBinding, EditTaskViewModel>(
    layoutResID = R.layout.fragment_edit,
    viewModelReference = BR.viewModel
) {

    override val viewModel: EditTaskViewModel by viewModel()

    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize(args.id)
    }

    override fun checkViewState(state: EditTaskState) {
        when (state) {
            is Loading -> binding.plEditItemLoader.visibility = VISIBLE
            is NavigateUp -> navigateUp()
        }
    }
}