package com.yasunov.temp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.designsystem.theme.ShiftAppInternTheme.colors
import com.yasunov.designsystem.theme.Typography

@Composable
fun OrdersScreen(modifier: Modifier = Modifier) {
    ShiftScaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colors.uiBackground,
                contentColor = Color.Transparent,
                elevation = 5.dp
            ) {
                Spacer(Modifier.padding(start = 16.dp))
                Text(
                    "Заказы", style = Typography.h5, color = colors.titleText,
                    modifier = modifier.padding(start = 32.dp, end = 16.dp)
                )
            }
        }
    ) { padding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
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
            item {}
            item {
                Text(
                    text = "Статус",
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = modifier.size(8.dp)
                            .background(shape = RoundedCornerShape(4.dp), color = colors.yellow)
                    )
                    Spacer(modifier.width(12.dp))
                    Text(
                        text = "Заказ оформлен",
                        style = Typography.body1,
                        color = colors.bodyPrimaryText,
                    )
                }
                Spacer(modifier.height(24.dp))
                Text(
                    text = "Адрес доставки",
                    textAlign = TextAlign.Center,
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Россия, г. Новосибирск, ул. Кирова, д. 86",
                    textAlign = TextAlign.Center,
                    style = Typography.body1,
                    color = colors.bodyPrimaryText,
                )
                Spacer(modifier.height(24.dp))
                Text(
                    text = "Состав заказа",
                    textAlign = TextAlign.Center,
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Пепперони",
                    textAlign = TextAlign.Center,
                    style = Typography.body1,
                    color = colors.bodyPrimaryText,
                    modifier = modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Сырная",
                    textAlign = TextAlign.Center,
                    style = Typography.body1,
                    color = colors.bodyPrimaryText,
                )

            }
            item {
                Divider(
                    color = colors.extraLight,
                    thickness = 1.dp,
                )
            }
            item {
                Text(
                    text = "Статус",
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = modifier.size(8.dp)
                            .background(color = colors.red, shape = RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier.width(12.dp))
                    Text(
                        text = "Заказ отменен",
                        style = Typography.body1,
                        color = colors.bodyPrimaryText,
                    )
                }
                Spacer(modifier.height(24.dp))
                Text(
                    text = "Адрес доставки",
                    textAlign = TextAlign.Center,
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Россия, г. Новосибирск, ул. Кирова, д. 86",
                    textAlign = TextAlign.Center,
                    style = Typography.body1,
                    color = colors.bodyPrimaryText,
                )
                Spacer(modifier.height(24.dp))
                Text(
                    text = "Состав заказа",
                    textAlign = TextAlign.Center,
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "С ананасом",
                    textAlign = TextAlign.Center,
                    style = Typography.body1,
                    color = colors.bodyPrimaryText,
                )
            }
            item {
                Divider(
                    color = colors.extraLight,
                    thickness = 1.dp,
                )
            }
            item {
                Text(
                    text = "Статус",
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = modifier.size(8.dp)
                            .background(color = colors.green, shape = RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier.width(12.dp))
                    Text(
                        text = "Заказ доставлен",
                        style = Typography.body1,
                        color = colors.bodyPrimaryText,
                    )
                }
                Spacer(modifier.height(24.dp))
                Text(
                    text = "Адрес доставки",
                    textAlign = TextAlign.Center,
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Россия, г. Новосибирск, ул. Кирова, д. 86",
                    textAlign = TextAlign.Center,
                    style = Typography.body1,
                    color = colors.bodyPrimaryText,
                )
                Spacer(modifier.height(24.dp))
                Text(
                    text = "Состав заказа",
                    textAlign = TextAlign.Center,
                    style = Typography.caption,
                    color = colors.tertiaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Ветчина и сыр",
                    textAlign = TextAlign.Center,
                    style = Typography.body1,
                    color = colors.bodyPrimaryText,
                )
            }


        }


    }


}
