package com.example.goncharov1.di

import androidx.lifecycle.ViewModelProvider
import com.example.goncharov1.viewmodels.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class GetViewModelModule {

    @Binds
    abstract fun getFactoryViewModel(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}