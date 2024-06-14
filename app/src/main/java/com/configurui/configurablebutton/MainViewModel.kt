package com.configurui.configurablebutton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ramprasad on 6/14/24.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val jsonRepository: JsonRepository) : ViewModel() {

    private val _configFlow = MutableStateFlow<UIComponentConfig>(UIComponentConfig.EmptyConfig)
    val configFlow: StateFlow<UIComponentConfig> = _configFlow

    init {
        fetchConfigFromJson(jsonRepository.readJsonFromAssets("config.json"))
    }


    /**
     * Fetch config from json
     *
     * @param jsonString
     */
    fun fetchConfigFromJson(jsonString: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val config = jsonRepository.parseConfigFromJson(jsonString!!)
                _configFlow.update {
                    config
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}