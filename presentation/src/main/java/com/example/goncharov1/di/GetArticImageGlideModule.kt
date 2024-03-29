package com.example.goncharov1.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.data.utils.DownloadImageLoaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class GetArticImageGlideModule {

    @Singleton
    @Provides
    fun getDownloadImageLoader(requestManager: RequestManager): DownloadImageLoader {
        return DownloadImageLoaderImpl(requestManager)
    }

    @Singleton
    @Provides
    fun getRequestManager(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }
}