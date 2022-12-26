package com.varkalys.argyle.di

import com.varkalys.argyle.mock.MockApiService
import com.varkalys.argyle.model.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ApiModule::class]
)
class MockApiModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return MockApiService()
    }
}