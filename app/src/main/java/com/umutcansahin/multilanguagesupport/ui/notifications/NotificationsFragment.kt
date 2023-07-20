package com.umutcansahin.multilanguagesupport.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.umutcansahin.multilanguagesupport.R
import com.umutcansahin.multilanguagesupport.data.DataStorePreferences
import com.umutcansahin.multilanguagesupport.databinding.FragmentNotificationsBinding
import com.umutcansahin.multilanguagesupport.managers.LanguageManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private var selectedLanguageCode: String = ""

    @Inject
    lateinit var languageManager: LanguageManager

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRadioButtonsWithCurrentLanguage()
        changeCurrentLanguage()
    }

    private fun setRadioButtonsWithCurrentLanguage() {
        when (languageManager.getCurrentLanguage()) {
            "tr" -> binding.radioButtonTurkish.isChecked = true
            "de" -> binding.radioButtonGerman.isChecked = true
            else -> binding.radioButtonEnglish.isChecked = true

        }
    }

    private fun changeCurrentLanguage() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonEnglish -> {
                    selectedLanguageCode = "en"
                }

                R.id.radioButtonGerman -> {
                    selectedLanguageCode = "de"
                }

                R.id.radioButtonTurkish -> {
                    selectedLanguageCode = "tr"
                }
            }
        }
        binding.buttonChangeLanguage.setOnClickListener {
            lifecycleScope.launch {
                dataStorePreferences.saveSelectedLanguage(selectedLanguageCode)
                dataStorePreferences.languageFlow.collect {languageCode->
                    languageManager.takeLanguageCode(languageCode)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}