package com.example.goncharov1.di

import com.example.goncharov1.domain.getArtic.GetArticUseCaseImpl
import com.example.goncharov1.ui.MainActivity
import dagger.Component

@Component(modules = [GetArticModule::class])
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
    fun getArticUseCase(): GetArticUseCaseImpl
}