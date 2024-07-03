package com.yasunov.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography

@Composable
fun ShiftButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 32.dp, 16.dp),
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ShiftAppInternTheme.colors.brand,
            contentColor = ShiftAppInternTheme.colors.uiBackground,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}


@Preview
@Composable
fun ShiftButtonPreview() {
    ShiftAppInternTheme {
        ShiftButton(onClick = {}) {
            Text("Оформить заказ", style = Typography.body1)
        }
    }
}
