package com.example.goncharov1.di

import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.data.utils.DownloadImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class GetArticImageGlideModule {
    @Binds
    abstract fun getDownloadImageLoader(downloadImageLoaderImpl: DownloadImageLoaderImpl): DownloadImageLoader
}