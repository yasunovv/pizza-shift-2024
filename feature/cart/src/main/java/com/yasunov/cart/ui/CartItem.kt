package com.yasunov.cart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yasunov.cart.model.CartItemModel
import com.yasunov.designsystem.R.string
import com.yasunov.designsystem.component.Counter
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography

@Composable
fun CartItem(
    cartItemEntity: CartItemModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .background(ShiftAppInternTheme.colors.uiBackground),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cartItemEntity.imageSrc)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier.size(64.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = modifier.padding(start = 16.dp)
        ) {
            Text(
                cartItemEntity.name,
                style = Typography.subtitle1,
                color = ShiftAppInternTheme.colors.titleText
            )
            Text(
                cartItemEntity.description,
                style = Typography.body2,
                color = ShiftAppInternTheme.colors.bodyPrimaryText
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Counter(
                    valueHandler = {}, initialValue = 1, minValue = 0, maxValue = 8,
                )
                Spacer(modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.change),
                    textDecoration = TextDecoration.Underline,
                    style = Typography.body2,
                    color = ShiftAppInternTheme.colors.quarterlyText,
//                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier.weight(1f))
                Text(
                    text = stringResource(
                        string.price,
                        cartItemEntity.price.toString()
                    ),
                    style = Typography.subtitle1,
                    color = ShiftAppInternTheme.colors.titleText,
//                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}

@Preview
@Composable
private fun CartItemPreview() {
    ShiftAppInternTheme {
        CartItem(
            cartItemEntity = CartItemModel(
                id = 1,
                imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
                name = "ШИФТ Суприм",
                description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами",
                price = 299,
                count = 1,
            )
        )
    }
}