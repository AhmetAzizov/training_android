package com.example.training_android.View.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.training_android.Model.Message

@Composable
fun MessageCard(
    message: Message
) {
    val startPadding = if (message.isUser) {
        24.dp
    } else {
        12.dp
    }

    val endPadding = if (message.isUser) {
        12.dp
    } else {
        24.dp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = startPadding, end = endPadding),
        horizontalAlignment = if (message.isUser) {
            Alignment.End
        } else {
            Alignment.Start
        }
    ) {
        Card(
            modifier = Modifier,
//                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomStart = 0.dp, bottomEnd = 24.dp)), // Only top-left corner is flat,
            colors = CardDefaults.cardColors(
                containerColor = if (message.isUser) {
                    MaterialTheme.colorScheme.surfaceContainerHigh
                } else {
                    MaterialTheme.colorScheme.surfaceContainerHighest
                }
            )
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = message.text,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

