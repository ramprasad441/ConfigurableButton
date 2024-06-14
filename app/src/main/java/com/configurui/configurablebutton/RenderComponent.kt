package com.configurui.configurablebutton

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Ramprasad on 6/14/24.
 */
@Composable
fun RenderComponent(config: UIComponentConfig) {
    when (config) {
        is UIComponentConfig.ButtonConfig -> {
            RenderButton(config)
        }

        is UIComponentConfig.TextViewConfig -> {
            RenderTextView(config)
        }

        is UIComponentConfig.EmptyConfig -> Toast.makeText(LocalContext.current,"Fetching configuration...!",Toast.LENGTH_SHORT).show()
    }
}

/**
 * Render button
 *
 * @param config
 */
@Composable
fun RenderButton(config: UIComponentConfig.ButtonConfig) {
    val backgroundColor = Color(android.graphics.Color.parseColor(config.color))

    Button(
        onClick = { /* Handle click */ },
        modifier = Modifier
            .width(config.width.dp)
            .height(config.height.dp)
            .background(backgroundColor)
    ) {
        RenderTextView(config.text)
    }
}

@Composable
fun RenderTextView(config: UIComponentConfig.TextViewConfig) {
    val textColor = Color(android.graphics.Color.parseColor(config.color))

    Text(
        text = config.text,
        fontSize = config.fontSize.sp,
        color = textColor,
        fontWeight = FontWeight.Bold
    )
}
