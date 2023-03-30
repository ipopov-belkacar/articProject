package com.example.goncharov1.di

import android.content.Context
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.data.utils.GlideDownloadImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class GetArticImageGlideModule {
    @Provides
    fun getDownloadImageLoader(@ApplicationContext context: Context): DownloadImageLoader {
        return GlideDownloadImageLoader(context)
    }
}