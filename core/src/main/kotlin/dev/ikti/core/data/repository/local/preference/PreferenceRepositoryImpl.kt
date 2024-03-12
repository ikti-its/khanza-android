package dev.ikti.core.data.repository.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.ikti.core.domain.repository.local.preference.PreferenceRepository
import dev.ikti.core.util.CoreConstant.USER_NEW_KEY
import dev.ikti.core.util.CoreConstant.USER_PREF
import dev.ikti.core.util.CoreConstant.USER_TOKEN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    context: Context
) : PreferenceRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREF)

    private object PreferenceKeys {
        val USER_NEW = booleanPreferencesKey(USER_NEW_KEY)
        val USER_TOKEN = stringPreferencesKey(USER_TOKEN_KEY)
    }

    private val dataStore = context.dataStore

    override fun isNewUser(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[PreferenceKeys.USER_NEW] ?: true
        }
    }

    override suspend fun setNewUser(isNew: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.USER_NEW] = isNew
        }
    }

    override fun getUserToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[PreferenceKeys.USER_TOKEN] ?: ""
        }
    }

    override suspend fun setUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.USER_TOKEN] = token
        }
    }

    override suspend fun clearUserToken() {
        dataStore.edit { preferences ->
            preferences.remove(PreferenceKeys.USER_TOKEN)
        }
    }
}
