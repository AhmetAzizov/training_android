package com.example.training_android.View.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.training_android.Model.Message

@Composable
fun MessageCard(
    message: Message
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = if (message.isUser) {
            Alignment.End
        } else {
            Alignment.Start
        }
    ) {
        Card(
            modifier = Modifier
                .background(
                    color =
                    if (message.isUser) {
                        Color.Blue
                    } else {
                        Color.Red
                    }
                )
        ) {
            Text(
                message.text,
                color = Color.Transparent
            )
        }
    }
}

