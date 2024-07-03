package com.yasunov.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yasunov.catalog.model.PizzaCatalog
import com.yasunov.catalog.ui.PizzaCatalogCard
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography


@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    navigateOnClick: (Int) -> Unit = {}
) {
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
                items(items = catalog, key = { it.id }) {
                    PizzaCatalogCard(it, navigateOnClick)
                }

            }
        }
    }

}


private val catalog: List<PizzaCatalog> = listOf(
    PizzaCatalog(
        id = 1,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaCatalog(
        id = 2,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaCatalog(
        id = 3,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaCatalog(
        id = 4,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaCatalog(
        id = 5,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaCatalog(
        id = 6,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
)