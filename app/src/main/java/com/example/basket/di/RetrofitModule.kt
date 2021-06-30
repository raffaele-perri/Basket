package com.example.basket.di

import com.example.basket.BuildConfig
import com.example.basket.framework.network.IBasketAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASEURL = "https://free-nba.p.rapidapi.com/"
private const val X_RAPIDAPI_KEY = "c103ef71aamshab690731e89a82ep1bf329jsn05f5336d78cf"
private const val X_RAPIDAPI_HOST = "free-nba.p.rapidapi.com"

@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : IBasketAPI {
        return Retrofit.Builder().baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).
            client(okHttpClient).build().create(IBasketAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttp() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if(BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor).addInterceptor{ chain ->
            val req = chain.request().newBuilder()
                .addHeader("x-rapidapi-key",X_RAPIDAPI_KEY)
                .addHeader("x-rapidapi-host",X_RAPIDAPI_HOST)
                .addHeader("useQueryString","true")
                .build()
            chain.proceed(req)
        }
        return builder.build()
    }
}