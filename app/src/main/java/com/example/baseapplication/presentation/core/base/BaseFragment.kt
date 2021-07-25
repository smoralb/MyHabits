package com.example.baseapplication.presentation.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.baseapplication.BR

abstract class BaseFragment<DB: ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes val layoutResID: Int
) : Fragment() {

    private var _binding: DB? = null
    abstract val viewModel: VM

    /**
     * This only can be accessed from child Fragments and only valid in onCreateView and onDestroyView
     * In that way, the binding variable it's only exposed to be used, not to modify the value
     */
    val binding: DB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResID, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}