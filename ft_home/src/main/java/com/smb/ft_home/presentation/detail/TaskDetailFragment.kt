package com.smb.ft_home.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_home.BR
import com.smb.ft_home.R
import com.smb.ft_home.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskDetailFragment : BaseFragment<TaskDetailState, FragmentDetailBinding, TaskDetailViewModel>
    (R.layout.fragment_detail, BR.viewModel) {

    override val viewModel by viewModel<TaskDetailViewModel>()
    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(args.id)

        binding.tbHeader.setNavigationOnClickListener {
            navigateTo(TaskDetailFragmentDirections.toHomeFragment())
        }
    }

    override fun checkViewState(state: TaskDetailState) {
        // Nothing to do yet
    }
}