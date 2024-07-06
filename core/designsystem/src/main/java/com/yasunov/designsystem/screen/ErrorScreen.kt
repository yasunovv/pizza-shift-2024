package com.yasunov.designsystem.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography

@Composable
fun ErrorScreen(
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Возникла ошибка при загрузке",
            style = Typography.body1,
            color = ShiftAppInternTheme.colors.titleText
        )
        Spacer(modifier = modifier.height(8.dp))
        ShiftButton(onClick = onClickButton) {
            Text(
                text = "Повторить",
                style = Typography.body1,
                color = Color.White
            )
        }
    }
}
