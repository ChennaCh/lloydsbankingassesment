package com.frost.leap.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Chenna Rao on 25/05/2023.
 * <p>
 * Frost Interactive
 */
@Singleton
class DataStoreManager @Inject constructor(private val dataStore: DataStore<Preferences>) {

    //    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
    private val modelObjectSecurityKey = stringPreferencesKey("ACEONLINE2019")

    private val gson = Gson()

    private fun serialize(value: Any): String {
        return gson.toJson(value)
    }

    private fun deserialize(value: String): Any {

        return gson.fromJson(value, Any::class.java)
    }

    //To Check the particular key is store in our DataStore Manager or not
    suspend fun isKeyPresent(key: String): Boolean {
        val preferences = dataStore.data.first()
        return preferences.contains(stringPreferencesKey(key))
    }

    // Function to retrieve all key sets
    suspend fun readAllKeys(): Set<Preferences.Key<*>>? {
        val keys = dataStore.data
            .map {
                it.asMap().keys
            }
        return keys.firstOrNull()
    }

    //Store String Values
    suspend fun saveStringKeyValuePair(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    //Fetch String Values
    fun getStringValueByKey(key: String): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]
        }
    }

    //Store Integer Values
    suspend fun saveIntKeyValuePair(key: String, value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    //Fetch Integer Values
    fun getIntValueByKey(key: String, defaultValue: Int = 0): Flow<Int> {
        val preferencesKey = intPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: defaultValue
        }
    }

    //Store Long Values
    suspend fun saveLongKeyValuePair(key: String, value: Long) {
        dataStore.edit { preferences ->
            preferences[longPreferencesKey(key)] = value
        }
    }

    //Fetch Long Values
    fun getLongValueByKey(key: String, defaultValue: Long = 0): Flow<Long> {
        val preferencesKey = longPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[preferencesKey] ?: defaultValue
        }
    }

    //Store Boolean Values
    suspend fun saveBooleanKeyValuePair(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    //Store Boolean Values
    fun getBooleanValueByKey(
        key: String,
        defaultValue: Boolean = false
    ): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            val preferencesKey = booleanPreferencesKey(key)
            preferences[preferencesKey] ?: defaultValue
        }
    }

    //To delete All Datastore Key values
    suspend fun deleteAllData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    //To delete a particular key from the  Datastore
    suspend fun deleteKey(key: String) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
        }
    }

    // Save Model object
    suspend fun saveModelData(model: Any) {
        dataStore.edit {
            val serializedData = serialize(model)
            it[modelObjectSecurityKey] = serializedData

        }
    }

    // Fetch Model object
    fun getModelData(): Flow<Any> {
        return dataStore.data.map { preferences ->
            val deserializedData = preferences[modelObjectSecurityKey] ?: ""
            deserialize(deserializedData)
        }
    }
}