package com.example.training_android

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.datastore.preferences.preferencesDataStore
import com.example.training_android.ui.theme.Training_androidTheme

class MainActivity : ComponentActivity() {
    val Context.dataStore by preferencesDataStore(name = "settings")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val favoriteDao = FavoritesDatabase.getDatabase(this).dao

        val viewModel: MainViewModel by viewModels {
            MainViewModelFactory(dataStore, favoriteDao)
        }

        enableEdgeToEdge()
        setContent {
            Training_androidTheme {

                    Greeting(viewModel)
            }
        }
    }
}

@Composable
fun Greeting(
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 48.dp),
            text = "Rekor: ${viewModel.maxNumber.value}"
        )

        Text(
            modifier = Modifier
                .padding(top = 48.dp),
            fontSize = 32.sp,
            text = viewModel.number.value.toString()
        )

        Button(
            onClick = {
                viewModel.updateValue()
            },
            modifier = Modifier
                .padding(48.dp)
        ) {
            Text(text = "sayıyı artır")
        }

        Button(
            onClick = {
                viewModel.resetNumber()
            },
            modifier = Modifier
                .padding(32.dp)
        ) {
            Text(text = "sayıyı sıfırla")
        }

        LazyColumn(
            modifier = Modifier
                .padding(top = 32.dp)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Training_androidTheme {
//        Greeting(
//            viewModel = MainViewModel()
//        )
    }
}