package com.yasunov.catalog.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yasunov.catalog.model.PizzaCardUiState
import com.yasunov.designsystem.component.PizzaTab
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.component.ToppingCard
import com.yasunov.designsystem.icon.AppIconsResource
import com.yasunov.designsystem.screen.ErrorScreen
import com.yasunov.designsystem.screen.LoadingScreen
import com.yasunov.designsystem.theme.BOTTOM_BAR_PADDING
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography
import com.yasunov.designsystem.theme.White
import com.yasunov.designsystem.util.gridItems
import com.yasunov.pizzacard.R
private const val MATERIAL_TOP_BAR = 24

@Composable
fun PizzaCardScreen(
    id: Int,
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit = {},
    onButtonNextClicked: (Int) -> Unit = {},

) {
    Scaffold(
        drawerBackgroundColor = ShiftAppInternTheme.colors.uiBackground,
        drawerContentColor = ShiftAppInternTheme.colors.uiBackground,
        drawerScrimColor = ShiftAppInternTheme.colors.uiBackground,
        backgroundColor = ShiftAppInternTheme.colors.uiBackground,
        contentColor = ShiftAppInternTheme.colors.uiBackground,
        contentWindowInsets = WindowInsets(0, MATERIAL_TOP_BAR, 0, BOTTOM_BAR_PADDING),
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
                Text(
                    "Пицца", style = Typography.h5, color = ShiftAppInternTheme.colors.titleText,
                    modifier = modifier.padding(start = 32.dp, end = 16.dp)
                )
            }
        }
    ) { paddingValues ->
        val viewModel = hiltViewModel<PizzaCardViewModel, PizzaCardViewModel.Factory>(
            creationCallback = { factory -> factory.create(id = id) }
        )
        val uiState by viewModel.uiState.collectAsState()
        when (val value = uiState) {
            is PizzaCardUiState.Loading -> LoadingScreen(modifier = modifier.padding(paddingValues))

            is PizzaCardUiState.Success -> SuccessScreen(
                viewModel = viewModel,
                uiState = value,
                padding = paddingValues
            )

            is PizzaCardUiState.Error -> ErrorScreen(
                onClickButton = { viewModel.loadPizzaCard() },
                modifier = modifier.padding(paddingValues)
            )
        }


    }
}

@Composable
private fun SuccessScreen(
    viewModel: PizzaCardViewModel,
    uiState: PizzaCardUiState.Success,
    modifier: Modifier = Modifier,
    onButtonNextClicked: (Int) -> Unit = {},
    onIngredientCardClicked: (Int) -> Unit = {},
    padding: PaddingValues = PaddingValues(0.dp),
) {
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
                val model = uiState.pizzaCard.img
                AsyncImage(
                    model = model,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = modifier.size(220.dp),
                )
            }
            Spacer(Modifier.height(32.dp))
            Text(
                uiState.pizzaCard.name,
                style = Typography.h5,
                color = ShiftAppInternTheme.colors.titleText
            )
            Spacer(Modifier.height(8.dp))

            val ingredients =
                uiState.pizzaCard.ingredients.joinToString(separator = ", ") { it.name }
            Text(
                ingredients,
                style = Typography.body1,
                color = ShiftAppInternTheme.colors.bodyPrimaryText
            )
        }
        item {
            Spacer(Modifier.height(24.dp))
        }
        item {
            val sizes = uiState.pizzaCard.sizes.map { it.name }
            PizzaTab(
                tabTitles = sizes, onClickTabItem = { viewModel.selectPizzaAndUpdateTotal(it) },
                modifier = modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {})
            )
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
        val toppingCardModelList = uiState.pizzaCard.toppings

        gridItems(toppingCardModelList, nColumns = 3) { item ->
            val ingredientCardId = item.id

            ToppingCard(
                toppingCard = item,
                onClickCard = { toppingCard, isSelected ->
                    viewModel.addTopping(
                        toppingCard = toppingCard,
                        isAdd = isSelected
                    )
                },
                modifier = modifier
                    .padding(4.dp)
                    .clickable(interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            onIngredientCardClicked(ingredientCardId)
                        }),
            )

        }
        item {
            Spacer(Modifier.height(24.dp))
        }

        item {
            val id = uiState.pizzaCard.id
            ShiftButton(
                onClick = {
                    onButtonNextClicked(id)
                },
                enabled = true,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.addPizzaToTrash, uiState.total),
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