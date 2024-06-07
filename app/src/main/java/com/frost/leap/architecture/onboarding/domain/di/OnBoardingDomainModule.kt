package com.frost.leap.architecture.onboarding.domain.di

import com.frost.leap.architecture.onboarding.data.network.IUserService
import com.frost.leap.architecture.onboarding.data.repository.UserRepoImplManager
import com.frost.leap.architecture.onboarding.domain.repository.UserRepositoryManager
import com.frost.leap.storage.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object OnBoardingDomainModule {
    @Provides
    fun provideAuthenticationRepo(
        iUserService: IUserService,
        dataStoreManager: DataStoreManager
    ): UserRepositoryManager {
        return UserRepoImplManager(iUserService = iUserService, dataStoreManager = dataStoreManager)
    }
}