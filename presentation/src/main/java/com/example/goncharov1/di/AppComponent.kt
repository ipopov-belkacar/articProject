package com.example.goncharov1.di

import com.example.goncharov1.ui.activity.MainActivity
import com.example.goncharov1.viewmodels.factory.ViewModelFactory
import dagger.Component

@Component(modules = [GetArticModule::class])
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
    fun injectViewModelFactory(): ViewModelFactory
}