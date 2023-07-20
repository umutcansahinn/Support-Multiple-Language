package com.umutcansahin.multilanguagesupport.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(
    name = "app_language_preferences"
)

class DataStorePreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val SELECTED_LANGUAGE_CODE = stringPreferencesKey("selected_language_code")
    }

    suspend fun saveSelectedLanguage(selectedLanguageCode: String) {
        context.datastore.edit { preferences ->
            preferences[SELECTED_LANGUAGE_CODE] = selectedLanguageCode
        }
    }

    val languageFlow: Flow<String> = context.datastore.data.map { preferences ->
        preferences[SELECTED_LANGUAGE_CODE] ?: "en"
    }
}
