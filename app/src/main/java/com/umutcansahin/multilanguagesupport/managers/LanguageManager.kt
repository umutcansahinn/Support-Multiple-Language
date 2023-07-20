package com.umutcansahin.multilanguagesupport.managers

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun takeLanguageCode(languageCode: String) {
        when (languageCode) {
            "tr" -> setLanguage("tr")
            "de" -> setLanguage("de")
            else -> setLanguage("en")
        }
    }

    private fun setLanguage(languageCode: String) {
//        val locale = Locale(languageCode)
//        Locale.setDefault(locale)
//        val resources = context.resources
//        val config: Configuration = resources.configuration
//        config.setLocale(locale)
//        resources.updateConfiguration(config, resources.displayMetrics)
//

        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
    }

    fun getCurrentLanguage(): String {
        return context.resources.configuration.locales.get(0).language
    }
}