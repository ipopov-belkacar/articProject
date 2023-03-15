package com.example.goncharov1.di

import com.example.goncharov1.domain.getArtic.GetArticUseCaseImpl
import dagger.Component

@Component(modules = [GetArticModule::class])
interface AppComponent {
    fun getArticUseCase(): GetArticUseCaseImpl
}