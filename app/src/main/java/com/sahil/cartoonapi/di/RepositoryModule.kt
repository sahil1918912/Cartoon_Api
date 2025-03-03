package com.sahil.cartoonapi.di

import com.sahil.cartoonapi.data.remote.api.RickAndMortyApi
import com.sahil.cartoonapi.data.repository.CharacterRepositoryImpl
import com.sahil.cartoonapi.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(api: RickAndMortyApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }
}