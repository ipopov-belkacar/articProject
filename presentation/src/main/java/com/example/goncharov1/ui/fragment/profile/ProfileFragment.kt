package com.example.goncharov1.ui.fragment.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.databinding.FragmentProfileBinding
import com.example.goncharov1.ui.base.BaseFragment
import com.example.goncharov1.utils.SharedPreferencesHelper
import com.example.goncharov1.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
@AndroidEntryPoint
class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    override val viewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()

        with(binding) {
            toolbar.setNavigationOnClickListener {
                goBack()
            }

            edit.setOnClickListener {
                ProfileFragmentDirections.actionProfileFragmentToProfileEditFragment().navigate()
            }
        }
    }

    private fun initData() {
        if (sharedPreferencesHelper.userName != getString(R.string.unknown)) {
            binding.name.text = sharedPreferencesHelper.userName
            binding.lastName.text = sharedPreferencesHelper.userLastName
        }
    }
}