package com.yasunov.catalog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yasunov.catalog.model.PizzaItemModel
import com.yasunov.catalog.model.UiState
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography

private const val BOTTOM_BAR_PADDING = 56

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
        contentWindowInsets = WindowInsets(
            left = 0,
            top = 56,
            right = 0,
            bottom = BOTTOM_BAR_PADDING
        ),
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
        val viewModel = hiltViewModel<CatalogViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        when (val value = uiState) {
            is UiState.Loading -> LoadingScreen()

            is UiState.Success -> SuccessScreen(
                uiState = value,
                navigateOnClick = navigateOnClick, padding = padding
            )

            is UiState.Error -> ErrorScreen(viewModel = viewModel)
        }
    }

}

@Composable
private fun ErrorScreen(
    viewModel: CatalogViewModel,
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
        ShiftButton(onClick = { viewModel.loadPizza() }) {
            Text(
                text = "Повторить",
                style = Typography.body1,
                color = Color.White
            )
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = ShiftAppInternTheme.colors.brand,
            modifier = modifier.size(64.dp)
        )
    }
}

@Composable
private fun SuccessScreen(
    uiState: UiState.Success,
    navigateOnClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(0.dp),

    ) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .consumeWindowInsets(padding)
            .windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal,
                ),
            ),
    ) {
        item {
            Spacer(modifier.padding(0.dp))
        }
        items(items = uiState.list, key = { it.id }) {
            PizzaItem(it, navigateOnClick)
        }

    }

}

private val catalog: List<PizzaItemModel> = listOf(
    PizzaItemModel(
        id = 1,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaItemModel(
        id = 2,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaItemModel(
        id = 3,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaItemModel(
        id = 4,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaItemModel(
        id = 5,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
    PizzaItemModel(
        id = 6,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами.",
        price = 3400
    ),
)