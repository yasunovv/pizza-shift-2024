package com.yasunov.shiftappintern.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yasunov.designsystem.icon.AppIconsResource
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography


@Composable
internal fun BottomBar(modifier: Modifier) {
    BottomAppBar(
        backgroundColor = ShiftAppInternTheme.colors.uiBackground,
        contentColor = Color.Transparent,
        elevation = 0.dp,
    ) {
        Spacer(Modifier.padding(start = 16.dp))
        BottomAppBarItem(
            AppIconsResource.Pizza,
            name = "Пицца",
            isActive = true,
            onClick = {}
        )
        Spacer(modifier.weight(1f))
        BottomAppBarItem(
            AppIconsResource.Clock,
            name = "Заказы",
            isActive = false,
            onClick = {}
        )
        Spacer(modifier.weight(1f))
        BottomAppBarItem(
            AppIconsResource.Trash,
            name = "Корзина",
            isActive = false,
            onClick = {}
        )
        Spacer(modifier.weight(1f))
        BottomAppBarItem(
            AppIconsResource.User,
            name = "Профиль",
            isActive = false,
            onClick = {}
        )
        Spacer(Modifier.padding(end = 16.dp))
    }
}

@Composable
private fun BottomAppBarItem(
    icon: Int,
    name: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val icon = icon
    val currentColor =
        if (isActive) ShiftAppInternTheme.colors.brand else ShiftAppInternTheme.colors.iconSecondary
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(onClick = onClick)
            .height(89.dp)
    ) {
        Icon(
            painter = painterResource(id = icon), contentDescription = null, tint = currentColor,
            modifier = modifier.size(20.dp)
        )
        Spacer(modifier = modifier.height(2.dp))
        Text(name, style = Typography.caption, color = currentColor)
    }
}