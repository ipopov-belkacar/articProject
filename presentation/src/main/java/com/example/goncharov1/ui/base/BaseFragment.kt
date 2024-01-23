package com.example.goncharov1.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.goncharov1.R
import kotlinx.coroutines.launch

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    private val navController get() = findNavController()
    protected open val viewModel: BaseViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel?.screenState?.collect(::processScreenState)
            }
        }
    }

    private fun processScreenState(screenState: ScreenState) {
        when (screenState) {

            is ScreenState.Success -> {
                success()
            }

            is ScreenState.Loading -> {
                loading(true)
            }

            is ScreenState.None -> {
                loading(false)
            }

            is ScreenState.Error -> {
                loading(false)
                error(screenState.message)
            }
        }
    }

    protected open fun success() {}

    protected open fun loading(loading: Boolean) {}

    protected open fun error(errorMessage: String?) {
        showToast(errorMessage ?: getString(R.string.default_error_message))
    }

    protected open fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun NavDirections.navigate() {
        navController.navigate(this)
    }

    protected fun goBack() {
        navController.popBackStack()
    }
}