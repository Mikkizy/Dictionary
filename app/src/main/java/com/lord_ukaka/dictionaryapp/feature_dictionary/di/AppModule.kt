package com.lord_ukaka.dictionaryapp.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.lord_ukaka.dictionaryapp.feature_dictionary.data.local.Converters
import com.lord_ukaka.dictionaryapp.feature_dictionary.data.local.WordInfoDatabase
import com.lord_ukaka.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.lord_ukaka.dictionaryapp.feature_dictionary.data.remote.DictionaryApi.Companion.BASE_URL
import com.lord_ukaka.dictionaryapp.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.lord_ukaka.dictionaryapp.feature_dictionary.data.util.GSONParser
import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.usecases.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUsecase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application) : WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "dictionary_database"
        ).addTypeConverter(Converters(GSONParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}