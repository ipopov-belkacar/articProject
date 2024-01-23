package com.example.goncharov1.ui.fragment.profile.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.databinding.FragmentProfileEditBinding
import com.example.goncharov1.ui.base.BaseFragment
import com.example.goncharov1.viewmodels.ProfileEditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileEditFragment : BaseFragment(R.layout.fragment_profile_edit) {

    private val binding: FragmentProfileEditBinding by viewBinding()
    override val viewModel: ProfileEditViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            saveUserData()
        }

        binding.toolbar.setNavigationOnClickListener {
            goBack()
        }
    }

    private fun saveUserData() {
        binding.apply {
            val name = edName.text.trim().toString()
            val lastName = edLastName.text.trim().toString()

            viewModel.checkValidAndSaveData(name = name, lastName = lastName)
        }
    }

    override fun success() {
        showToast(requireContext().getString(R.string.data_saved))
    }
}