package com.pi.cardmatcher.di.module

import android.content.Context
import androidx.room.Room
import com.pi.cardmatcher.data.CardMatcherRepository
import com.pi.cardmatcher.data.api.CardMatcherApiService
import com.pi.cardmatcher.data.api.CardMatcherRemoteDataSource
import com.pi.cardmatcher.data.db.AppDatabase
import com.pi.cardmatcher.data.db.CardMatcherLocalSource
import com.pi.cardmatcher.utils.Constants
import com.pi.cardmatcher.utils.Constants.Companion.DATABASE_NAME
import com.pi.cardmatcher.utils.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideShadiCardMatcherApiService(): CardMatcherApiService {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient().newBuilder().addInterceptor(logger).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CardMatcherApiService::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRepository(
        localSource: CardMatcherLocalSource,
        remoteDataSource: CardMatcherRemoteDataSource,
        dispatcherProvider: CoroutinesDispatcherProvider
    ): CardMatcherRepository {
        return CardMatcherRepository(localSource, remoteDataSource, dispatcherProvider)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideShadiCardMatcherLocalCache(
        database: AppDatabase,
        dispatcherProvider: CoroutinesDispatcherProvider
    ): CardMatcherLocalSource {
        return CardMatcherLocalSource(database.getShadiCardMatcherDao(), dispatcherProvider)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideShadiMatcherRemoteDataSource(
        service: CardMatcherApiService
    ): CardMatcherRemoteDataSource {
        return CardMatcherRemoteDataSource(service)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCoroutinesDispatcherProvider(): CoroutinesDispatcherProvider {
        return CoroutinesDispatcherProvider(
            Dispatchers.Main,
            Dispatchers.IO,
            Dispatchers.Default
        )
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }

}


