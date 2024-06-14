package com.configurui.configurablebutton

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Created by Ramprasad on 6/14/24.
 */
@Serializable
sealed class UIComponentConfig {
    @Serializable
    data class ButtonConfig(
        val color: String,
        val width: Int,
        val height: Int,
        val text: TextViewConfig
    ) : UIComponentConfig()

    @Serializable
    data class TextViewConfig(
        val text: String,
        val fontSize: Int,
        val color: String
    ) : UIComponentConfig()

    @Serializable
    data object EmptyConfig : UIComponentConfig()
}
