package com.example.training_android.View

import androidx.activity.SystemBarStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.training_android.AppState
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

    AppState.systemUiController!!.setStatusBarColor(MaterialTheme.colorScheme.surfaceVariant)

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.scrim)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Icon(
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        navController.navigate(ScreenNames.HOME_SCREEN)
                    }
                ,
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = "back button"
            )
        }

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
            ) {

                val listState = rememberLazyListState()

                LaunchedEffect(viewModel.messageList.value.size) {
                    listState.animateScrollToItem(viewModel.messageList.value.size - 1)
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(
                        12.dp,
                        alignment = Alignment.Bottom
                    )
                ) {
                    items(viewModel.messageList.value.size) { msg ->
                        MessageCard(message = viewModel.messageList.value[msg])
                    }
                }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done // Specify the IME action
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            viewModel.sendRequest(viewModel.chatTextField.value)
                        }
                    ),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
                    placeholder = { Text("Type a query") },
                    value = viewModel.chatTextField.value,
                    onValueChange = { viewModel.chatTextField.value = it }
                )
            }
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

