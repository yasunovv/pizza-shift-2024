package com.yasunov.catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yasunov.catalog.model.PizzaItemModel
import com.yasunov.designsystem.R
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography

@Composable
fun PizzaItem(
    pizzaCard: PizzaItemModel,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable(onClick = { onClick(pizzaCard.id) })
            .padding(horizontal = 16.dp)
            .background(ShiftAppInternTheme.colors.uiBackground),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pizzaCard.imageSrc)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier.size(116.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = modifier.padding(start = 24.dp)
        ) {
            Text(
                pizzaCard.name,
                style = Typography.subtitle1,
                color = ShiftAppInternTheme.colors.titleText
            )
            Text(
                pizzaCard.description,
                style = Typography.body2,
                color = ShiftAppInternTheme.colors.bodyPrimaryText
            )
            Text(
                text = stringResource(R.string.price, pizzaCard.price),
                style = Typography.subtitle1,
                color = ShiftAppInternTheme.colors.titleText
            )
        }
    }
}

@Preview
@Composable
private fun PizzaItemPreview() {
    ShiftAppInternTheme {
        PizzaItem(
            PizzaItemModel(
                id = 1,
                imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
                name = "ШИФТ Суприм",
                description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами",
                price = 299
            ),
            {},
        )
    }
}