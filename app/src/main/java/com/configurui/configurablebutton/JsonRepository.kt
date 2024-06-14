package com.configurui.configurablebutton

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ramprasad on 6/14/24.
 */
@Singleton
class JsonRepository @Inject constructor(private val context: Context) {

    fun readJsonFromAssets(fileName: String): String? {
        var json: String? = null
        try {
            val inputStream = context.assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }


    fun parseConfigFromJson(jsonString: String): UIComponentConfig {
        val json = Json { ignoreUnknownKeys = true }
        val jsonObject = json.parseToJsonElement(jsonString).jsonObject

        return when (jsonObject["type"]?.jsonPrimitive?.content) {
            "ButtonConfig" -> {
                val color = jsonObject["color"]?.jsonPrimitive?.content ?: "#FFFFFF"
                val width = jsonObject["width"]?.jsonPrimitive?.int ?: 100
                val height = jsonObject["height"]?.jsonPrimitive?.int ?: 50
                val textConfigJson = parseConfigFromJson(jsonObject["text"]?.jsonObject.toString()) as UIComponentConfig.TextViewConfig
                UIComponentConfig.ButtonConfig(color, width, height, textConfigJson)
            }
            "TextViewConfig" -> {
                val color = jsonObject["color"]?.jsonPrimitive?.content ?: "#FFFFFF"
                val text = jsonObject["text"]?.jsonPrimitive?.content ?:""
                val fontSize = jsonObject["fontSize"]?.jsonPrimitive?.int ?: 50
                UIComponentConfig.TextViewConfig(text = text, color = color, fontSize = fontSize)
            }
            else -> UIComponentConfig.EmptyConfig
        }
    }
}