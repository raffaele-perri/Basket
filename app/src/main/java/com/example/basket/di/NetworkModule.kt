package com.example.basket.di

import com.example.app_data.network.INetworkDataSource
import com.example.basket.framework.network.NetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun provideNetworkDataSource(ds: NetworkDataSource) : INetworkDataSource
}