package com.smb.ft_main.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_main.BR
import com.smb.ft_main.R
import com.smb.ft_main.databinding.FragmentSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskDetailFragment : BaseFragment<TaskDetailState, FragmentSecondBinding, TaskDetailViewModel>
    (R.layout.fragment_second, BR.viewModel) {

    override val viewModel by viewModel<TaskDetailViewModel>()
    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(args.isbn)

        binding.tbHeader.setNavigationOnClickListener {
            navigateTo(TaskDetailFragmentDirections.toFirstFragment())
        }
    }

    override fun checkViewState(state: TaskDetailState) {
        // Nothing to do yet
    }
}