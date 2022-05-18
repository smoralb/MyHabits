package com.smb.core.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<S : BaseState, DB : ViewDataBinding, out VM : BaseViewModel<S>>(
    @LayoutRes val layoutResID: Int,
    private val viewModelReference: Int
) : Fragment() {

    private var _binding: DB? = null
    abstract val viewModel: VM

    /**
     * This only can be accessed from child Fragments and only valid in onCreateView and onDestroyView
     * In that way, the binding variable it's only exposed to be used, not to modify the value
     */
    val binding: DB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewState.observeForever { checkViewState(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResID, container, false)
        return _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(viewModelReference, viewModel)
        }?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.viewState.removeObservers(this)
    }

    fun navigateTo(directions: NavDirections) = findNavController().navigate(directions)

    abstract fun checkViewState(state: S)

}