package com.yasunov.cart.ui

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yasunov.cart.R
import com.yasunov.cart.model.CartItemModel
import com.yasunov.cart.model.CartUiState
import com.yasunov.designsystem.component.ShiftButton
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.designsystem.icon.AppIconsResource
import com.yasunov.designsystem.screen.ErrorScreen
import com.yasunov.designsystem.screen.LoadingScreen
import com.yasunov.designsystem.theme.ShiftAppInternTheme.colors
import com.yasunov.designsystem.theme.Typography

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit,
) {
    ShiftScaffold(
        topBar = {
            val painter = painterResource(AppIconsResource.ArrowLeft)
            TopAppBar(
                backgroundColor = colors.uiBackground,
                contentColor = Color.Transparent,
                elevation = 5.dp
            ) {
                Spacer(Modifier.padding(start = 16.dp))
                Text(
                    "Пицца", style = Typography.h5, color = colors.titleText,
                    modifier = modifier.padding(start = 32.dp, end = 16.dp)
                )
            }
        }
    ) { paddingValues ->
        val viewModel = hiltViewModel<CartViewModel>()
        LaunchedEffect(Unit) {
//            todo add loader
        }
        val uiState by viewModel.uiState.collectAsState()
        when (val value = uiState) {
            is CartUiState.Loading -> LoadingScreen(modifier = modifier.padding(paddingValues))

            is CartUiState.Success -> SuccessScreen(
                viewModel = viewModel,
                uiState = value,
                padding = paddingValues
            )

            is CartUiState.Error -> ErrorScreen(
                onClickButton = {
//                    todo add handler error
                },
                modifier = modifier.padding(paddingValues)
            )
        }


    }

}

@Composable
private fun SuccessScreen(
    viewModel: CartViewModel,
    uiState: CartUiState.Success,
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
        item {}
        items(items = list, key = { it.id }) {
            CartItem(
                cartItemEntity = it,
            )
            Spacer(modifier.height(24.dp))
            Divider(
                color = colors.extraLight,
                thickness = 1.dp,
                startIndent = 16.dp,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
        item {
            Column(
                modifier.padding(horizontal = 16.dp)
            ) {
                Spacer(modifier.weight(1f))
                Text(
                    text = stringResource(R.string.pizza_cost, uiState.total),
                    textAlign = TextAlign.Center,
                    style = Typography.body1,
                    color = colors.bodyPrimaryText,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier.height(16.dp))
                ShiftButton(
                    onClick = {
                        //                    todo add handler button
                    },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.checkout_pizza),
                        style = Typography.body1,
                        color = Color.White,
                    )

                }
            }

        }

    }

}

private val list = List(6) { index ->
    CartItemModel(
        id = index,
        imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
        name = "ШИФТ Суприм",
        description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами",
        price = 299,
        count = 1
    )
}
