package com.yasunov.shiftappintern.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yasunov.catalog.navigation.PizzaCardDest
import com.yasunov.catalog.ui.PizzaCardScreen
import com.yasunov.catalog.navigation.CatalogDest
import com.yasunov.catalog.ui.CatalogScreen
import com.yasunov.designsystem.theme.ShiftAppInternTheme

@Composable
fun ShiftApp(
    modifier: Modifier = Modifier
) {

    Scaffold(
        drawerBackgroundColor = ShiftAppInternTheme.colors.uiBackground,
        drawerContentColor = ShiftAppInternTheme.colors.uiBackground,
        drawerScrimColor = ShiftAppInternTheme.colors.uiBackground,
        backgroundColor = ShiftAppInternTheme.colors.uiBackground,
        contentColor = ShiftAppInternTheme.colors.uiBackground,
        contentWindowInsets = WindowInsets(0,0,0,89),
        bottomBar = {
            BottomBar(modifier)
        }
    ) { padding ->
        Column(modifier.padding(padding)) {
            NavHost(navController = rememberNavController(), startDestination = PizzaCardDest) {
                composable<CatalogDest> {
                    CatalogScreen()
                }
                composable<PizzaCardDest> {
                    PizzaCardScreen()
                }
            }
        }

    }


}

