package com.yasunov.temp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.designsystem.component.ShiftTextField
import com.yasunov.designsystem.theme.ShiftAppInternTheme.colors
import com.yasunov.designsystem.theme.Typography

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var lastName by remember { mutableStateOf("Сергеев") }
    var firstName by remember { mutableStateOf("Алексей") }
    var phoneNumber by remember { mutableStateOf("+7 913 123 45 67") }
    var email by remember { mutableStateOf("Email") }
    var city by remember { mutableStateOf("г. Новосибирск, ул. Кирова, д. 86") }

    ShiftScaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colors.uiBackground,
                contentColor = Color.Transparent,
                elevation = 5.dp
            ) {
                Spacer(Modifier.padding(start = 16.dp))
                Text(
                    "Ваши данные", style = Typography.h5, color = colors.titleText,
                    modifier = modifier.padding(start = 32.dp, end = 16.dp)
                )
            }
        }
    ) { padding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            item {
                Spacer(modifier.height(8.dp))
            }
            item {
                UserDataItem(
                    label = "Фамилия*",
                    value = lastName,
                    onValueChange = { lastName = it })

            }
            item {
                UserDataItem(label = "Имя*", value = firstName, onValueChange = { firstName = it })
            }

            item {
                UserDataItem(
                    label = "Номер телефона*",
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    keyboardType = KeyboardType.Phone
                )

            }
            item {
                UserDataItem(
                    label = "Email",
                    value = email,
                    onValueChange = { email = it },
                    keyboardType = KeyboardType.Email
                )

            }
            item {
                UserDataItem(label = "Город", value = city, onValueChange = { city = it })
            }

            item {
                Column {
                    Spacer(Modifier.weight(1f))
                    ShiftButton(
                        onClick = { /* Handle continue action */ },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Продолжить",
                            style = Typography.body1,
                            color = Color.White,
                        )
                    }
                }


            }


        }
    }
}

@Composable
private fun UserDataItem(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Text(
            text = label,
            style = Typography.body1,
            color = colors.bodyPrimaryText,
        )
        ShiftTextField(
            value = value,
            onValueChange = { onValueChange(it) },
            keyboardType = keyboardType
        )
    }
}



