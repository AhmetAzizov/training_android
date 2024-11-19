package com.example.training_android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 32.dp),
            text = "Rekor: ${viewModel.maxNumber.value}"
        )

        Text(
            modifier = Modifier
                .padding(top = 32.dp),
            fontSize = 32.sp,
            text = viewModel.number.value.toString()
        )

        Button(
            onClick = {
                viewModel.updateValue()
            },
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            Text(text = "sayıyı artır")
        }

        Button(
            onClick = {
                viewModel.resetNumber()
            },
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            Text(text = "sayıyı sıfırla")
        }

        Button(
            onClick = {
                viewModel.addFavorites(viewModel.number.value)
            },
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            Text(text = "Favorilere Ekle")
        }

        Button(
            onClick = {
                viewModel.clearFavorites()
            },
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            Text(text = "Favorileri Sil")
        }

        Button(
            onClick = {
                navController.navigate(ScreenNames.CHAT_SCREEN)
            },
            modifier = Modifier
                .padding(top = 32.dp)
        ) {
            Text(text = "Chat Ekranına Geç")
        }

        LazyColumn(
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                items = viewModel.favoriteList.value,
                key = { it.id })
            {
                Text("Favorite: ${it.favoriteNumber}")
            }
        }
    }
}
