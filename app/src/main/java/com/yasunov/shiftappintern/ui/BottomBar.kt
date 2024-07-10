package com.yasunov.shiftappintern.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yasunov.cart.navigation.CartDest
import com.yasunov.catalog.navigation.CatalogDest
import com.yasunov.designsystem.icon.AppIconsResource
import com.yasunov.designsystem.theme.ShiftAppInternTheme
import com.yasunov.designsystem.theme.Typography
import kotlin.reflect.KClass


@Composable
internal fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    BottomAppBar(
        backgroundColor = ShiftAppInternTheme.colors.uiBackground,
        contentColor = Color.Transparent,
        elevation = 0.dp,
        modifier = modifier

    ) {
        Spacer(Modifier.padding(start = 16.dp))
        BottomAppBarItem(
            AppIconsResource.Pizza,
            name = "Пицца",
            isActive = checkDestination(
                navBackStackEntry = navBackStackEntry,
                route = CatalogDest::class
            ),
            modifier = Modifier.clickable(
                onClick = {
                    navController.navigate(CatalogDest)

                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
        )
        Spacer(modifier.weight(1f))
        BottomAppBarItem(
            AppIconsResource.Clock,
            name = "Заказы",
            isActive = false,
            modifier = Modifier.clickable(
                onClick = { },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })

        )
        Spacer(modifier.weight(1f))
        BottomAppBarItem(
            AppIconsResource.Cart,
            name = "Корзина",
            isActive = checkDestination(
                navBackStackEntry = navBackStackEntry,
                route = CartDest::class
            ),
            modifier = Modifier.clickable(
                onClick = { navController.navigate(CartDest) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })

        )
        Spacer(modifier.weight(1f))
        BottomAppBarItem(
            AppIconsResource.User,
            name = "Профиль",
            isActive = false,
            modifier = Modifier.clickable(
                onClick = { },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })

        )
        Spacer(Modifier.padding(end = 16.dp))
    }
}

@Composable
private fun BottomAppBarItem(
    icon: Int,
    name: String,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val icon = icon
    val currentColor =
        if (isActive) ShiftAppInternTheme.colors.brand else ShiftAppInternTheme.colors.iconSecondary
    val painter = painterResource(id = icon)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .height(89.dp)
    ) {
        Icon(
            painter = painter, contentDescription = null, tint = currentColor,
            modifier = modifier.size(20.dp)
        )
        Spacer(modifier = modifier.height(2.dp))
        Text(name, style = Typography.caption, color = currentColor)
    }
}

private fun <T : Any> checkDestination(
    navBackStackEntry: NavBackStackEntry?,
    route: KClass<T>
): Boolean {
    navBackStackEntry?.destination?.let { currentDestination ->
        if (currentDestination.hasRoute(route = route)) return true else false
    }
    return false
}