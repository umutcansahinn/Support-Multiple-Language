package com.umutcansahin.multilanguagesupport.managers

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageManager @Inject constructor(
//    @ApplicationContext private val context: Context
) {

    fun takeLanguageCode(languageCode: String) {
        when (languageCode) {
            "tr" -> setLanguage("tr")
            "de" -> setLanguage("de")
            else -> setLanguage("en")
        }
    }

    private fun setLanguage(languageCode: String) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
    }

    fun getCurrentLanguage(): String {
        // return context.resources.configuration.locales.get(0).language
        return AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "en"
    }
}
//interface AppLanguageProvider{
//    fun getAppLanguage(): String
//    fun setAppLanguage(languageCode: String?)
//}
//
//
//class AppLanguageProviderImpl @Inject constructor(
//    private val context: Context
//) : AppLanguageProvider {
//
//    override fun getAppLanguage(): String {
//        val configuration = context.resources.configuration
//        val locale = Locale.forLanguageTag(configuration.locale.language)
//        return when (locale) {
//            Locale("tr") -> "tr"
//            Locale("en") -> "en"
//            else -> "tr"
//        }
//    }
//
//    override fun setAppLanguage(languageCode: String?) {
//        val locale = Locale(languageCode)
//        Locale.setDefault(locale)
//        val resources = context.resources
//        val config: Configuration = resources.configuration
//        config.setLocale(locale)
//        resources.updateConfiguration(config, resources.displayMetrics)
//    }
//}