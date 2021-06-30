package com.example.basket.di

import com.example.app_data.implementation.BasketRepository
import com.example.app_domain.interfaces.IBasketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideRepository(repo : BasketRepository) : IBasketRepository
}