package com.example.goncharov1.viewmodels

import android.content.Context
import com.example.goncharov1.R
import com.example.goncharov1.ui.base.BaseViewModel
import com.example.goncharov1.utils.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : BaseViewModel() {

    fun checkValidAndSaveData(name: String, lastName: String) {
        if (name.length !in 2..20) {
            sendError(context.getString(R.string.check_entered_name))
            return
        }
        if (lastName.length !in 2..20) {
            sendError(context.getString(R.string.check_entered_last_name))
            return
        }

        sharedPreferencesHelper.userName = name
        sharedPreferencesHelper.userLastName = lastName

        success()
    }
}