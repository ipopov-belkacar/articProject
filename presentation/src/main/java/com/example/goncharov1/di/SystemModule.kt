package com.example.goncharov1.di

import android.content.Context
import android.content.SharedPreferences
import com.example.goncharov1.utils.ProjectConstants
import com.example.goncharov1.utils.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SystemModule {

    @Provides
    @Singleton
    fun getSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(ProjectConstants.DATABASE_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun getSharedPreferencesHelper(sharedPreferences: SharedPreferences): SharedPreferencesHelper {
        return SharedPreferencesHelper(sharedPreferences)
    }
}