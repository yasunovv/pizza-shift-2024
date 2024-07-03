package com.yasunov.catalog

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yasunov.catalog.model.PizzaCard
import com.yasunov.designsystem.component.IngredientCard
import com.yasunov.designsystem.component.PizzaTab
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.icon.AppIconsResource
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography
import com.yasunov.designsystem.theme.White
import com.yasunov.designsystem.util.gridItems
import com.yasunov.model.IngredientCardModel


@Composable
fun PizzaCardScreen(
    onBackIconClicked: () -> Unit = {},
    onButtonNextClicked: (Int) -> Unit = {},
    onIngredientCardClicked: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val pizzaCard = pizzaCard
    Scaffold(
        drawerBackgroundColor = ShiftAppInternTheme.colors.uiBackground,
        drawerContentColor = ShiftAppInternTheme.colors.uiBackground,
        drawerScrimColor = ShiftAppInternTheme.colors.uiBackground,
        backgroundColor = ShiftAppInternTheme.colors.uiBackground,
        contentColor = ShiftAppInternTheme.colors.uiBackground,
        contentWindowInsets = WindowInsets(0, 56, 0, 56),
        topBar = {
            TopAppBar(
                backgroundColor = ShiftAppInternTheme.colors.uiBackground,
                contentColor = Color.Transparent,
                elevation = 5.dp
            ) {
                Spacer(Modifier.padding(start = 16.dp))
                Icon(
                    painter = painterResource(AppIconsResource.ArrowLeft),
                    contentDescription = null,
                    tint = ShiftAppInternTheme.colors.light,
                    modifier = modifier.size(24.dp)
                        .clickable { onBackIconClicked() }
                )
                Spacer(Modifier.padding(start = 32.dp))
                Text("Пицца", style = Typography.h5, color = ShiftAppInternTheme.colors.titleText)
                Spacer(Modifier.padding(end = 16.dp))
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                )

        ) {
            item {
                Spacer(Modifier.height(24.dp))
            }
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxWidth()
                ) {
                    val model = pizzaCard.imageSrc
                    AsyncImage(
                        model = model,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier.size(220.dp),
                    )
                }
            }
            item {
                Spacer(Modifier.height(32.dp))
            }
            item {
                Text(
                    pizzaCard.name,
                    style = Typography.h5,
                    color = ShiftAppInternTheme.colors.titleText
                )
            }
            item {
                Spacer(Modifier.height(8.dp))
            }
            item {
                Text(
                    pizzaCard.ingredients.toString().removePrefix("[").removeSuffix("]"),
                    style = Typography.body1,
                    color = ShiftAppInternTheme.colors.bodyPrimaryText
                )
            }
            item {
                Spacer(Modifier.height(24.dp))
            }
            item {
                PizzaTab(pizzaCard.sizes)
            }
            item {
                Spacer(Modifier.height(24.dp))
            }
            item {
                Text(
                    "Добавить по вкусу",
                    style = Typography.body1,
                    fontWeight = FontWeight.Medium,
                    color = ShiftAppInternTheme.colors.titleText
                )
            }
            item {
                Spacer(Modifier.height(16.dp))
            }
            val ingredientCardObjList = List(9) { id ->
                IngredientCardModel(
                    id = id,
                    price = 79,
                    name = "Нежный цыпленок",
                    imageSrc = "https://shift-backend.onrender.com/static/images/ingredient/chicken_fillet.png"
                )
            }
            gridItems(ingredientCardObjList, nColumns = 3) {
                IngredientCard(
                    it,
                    modifier = modifier.clickable(onClick = { onIngredientCardClicked(it.id) })
                )

            }
            item {
                Spacer(Modifier.height(24.dp))
            }
            item {
                ShiftButton(
                    onClick = {
                        onButtonNextClicked(pizzaCard.id)
                    },
                    enabled = true,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        "Добавить пиццу",
                        style = Typography.body1,
                        color = White
                    )
                }
            }
            item {
                Spacer(Modifier.height(24.dp))
            }

        }
    }
}

@Preview
@Composable
private fun PizzaCardScreenPreview() {
    ShiftAppInternTheme {
        PizzaCardScreen()
    }
}

private val pizzaCard: PizzaCard = PizzaCard(
    id = 1,
    name = "Двойной цепленок",
    ingredients = listOf("Цыпленок", "моцарелла", "фирменный", "соус альфредо"),
    sizes = listOf("Маленькая", "Средняя", "Большая"),
    imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",

    )