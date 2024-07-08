package com.yasunov.catalog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yasunov.catalog.entity.PizzaItemUiState
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.designsystem.screen.ErrorScreen
import com.yasunov.designsystem.screen.LoadingScreen
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography

@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    navigateOnClick: (Int) -> Unit = {},
) {
    ShiftScaffold(
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
        DisposableEffect(Unit) {
            viewModel.loadPizzaItem()
            onDispose {  }
        }
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        when (val value = uiState) {
            is PizzaItemUiState.Loading -> LoadingScreen()

            is PizzaItemUiState.Success -> SuccessScreen(
                uiState = value,
                navigateOnClick = navigateOnClick, padding = padding
            )

            is PizzaItemUiState.Error -> ErrorScreen(onClickButton = { viewModel.loadPizzaItem() })
        }
    }

}

@Composable
private fun SuccessScreen(
    uiState: PizzaItemUiState.Success,
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
        item{}
        items(items = uiState.list, key = { it.id }) {
            PizzaItem(it, navigateOnClick)
        }

    }
}