package com.example.training_android.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.training_android.MainViewModel
import com.example.training_android.R
import com.example.training_android.ScreenNames
import com.example.training_android.View.Components.MessageCard

private const val TAG = "chatScreen"

@Composable
fun ChatScreen(
    viewModel: MainViewModel,
    navController: NavController
    ) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .systemBarsPadding(),
        color = Color.Transparent
    ) {
        Column {
            Icon(
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        navController.navigate(ScreenNames.HOME_SCREEN)
                    },
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                contentDescription = "back button")

            Column {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)

                ) {
                    items(viewModel.messageList.value.size) { msg ->
                        MessageCard(message = viewModel.messageList.value[msg])
                    }
                }

                OutlinedTextField(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                        .imePadding()
                        .align(Alignment.End),
                    value = viewModel.chatTextField.value,
                    onValueChange = { viewModel.chatTextField.value = it }
                )
            }






//            Button(
//                modifier = Modifier
//                    .padding(6.dp),
//                onClick = {
//                    if (AppState.isPremium.value) {
//                        viewModel.sendRequest(viewModel.chatTextField.value)
//                    } else {
//                        AppState.isPremium.value = true
//
//                        Toast.makeText(context, "Premium Üyelik Alındı", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            ) {
//                Text(text = stringResource(R.string.BtnSend))
//            }
        }

    }
}

