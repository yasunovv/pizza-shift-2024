package com.yasunov.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yasunov.catalog.model.PizzaCard
import com.yasunov.designsystem.icon.AppIconsResource
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography


@Composable
fun PizzaCardScreen(
    modifier: Modifier = Modifier,
    navigateOnClick: (Int) -> Unit = {},
    onBackIconClicked: () -> Unit,
    pizzaCard: PizzaCard
) {
//    val pizzaCard =
    Scaffold(
        drawerBackgroundColor = ShiftAppInternTheme.colors.uiBackground,
        drawerContentColor = ShiftAppInternTheme.colors.uiBackground,
        drawerScrimColor = ShiftAppInternTheme.colors.uiBackground,
        backgroundColor = ShiftAppInternTheme.colors.uiBackground,
        contentColor = ShiftAppInternTheme.colors.uiBackground,
//        Учитывем bottom bar
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
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                item {
                    Spacer(Modifier.padding(0.dp))
                }
                item {
                    AsyncImage(
                        model = pizzaCard.imageSrc,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier.size(220.dp)
                    )
                }
                item {
                    Text(
                        pizzaCard.name,
                        style = Typography.h5,
                        color = ShiftAppInternTheme.colors.titleText
                    )
                    Spacer(modifier.height(8.dp))
                    Text(
                        pizzaCard.name,
                        style = Typography.body1,
                        color = ShiftAppInternTheme.colors.bodyPrimaryText
                    )


                }
//                items(items = catalog, key = { it.id }) {
//                    PizzaCatalogCard(it, navigateOnClick)
//                }

            }
        }
    }

}


private val catalog: PizzaCard = PizzaCard(
    name = "Laurie McKay",
    ingredients = listOf(),
    sizes = listOf(),
    imageSrc = "docendi"
)