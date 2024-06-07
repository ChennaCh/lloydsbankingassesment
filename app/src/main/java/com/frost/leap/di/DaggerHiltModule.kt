package com.frost.leap.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.frost.leap.architecture.onboarding.data.network.IUserService
import com.frost.leap.batches_list.domain.network.ApiService
import com.frost.leap.provider.RetrofitAdapter
import com.frost.leap.storage.DataStoreManager
import com.frost.leap.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Chenna Rao on 06/06/2023.
 * <p>
 * Frost Interactive
 */
@Module
@InstallIn(SingletonComponent::class)
object DaggerHiltModule {

    private const val USER_PREFERENCES = "ace_online_datastore"

    /*
    * corruptionHandler (optional) — invoked if a CorruptionException is thrown by the serializer
            when the data cannot be de-serialized which instructs DataStore how to replace the corrupted
    * data migrations (optional) — a list of DataMigration for moving previous data into
    * DataStore scope (optional) — the scope in which IO operations and transform functions will execute;
    * in this case, we’re reusing the same scope as the DataStore API default one
    * produceFile — generates the File object for Preferences DataStore based on the provided Context and name,
            stored in this.applicationContext.filesDir + datastore/ subdirectory
    */
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext, USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

    @Provides
    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
        return DataStoreManager(dataStore)
    }


    @Provides
    fun newApiService(): ApiService {
        return Retrofit.Builder().client(RetrofitAdapter.getClient()).baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    fun iUserService(): IUserService {
        return Retrofit.Builder().client(RetrofitAdapter.getClient()).baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IUserService::class.java)
    }


}