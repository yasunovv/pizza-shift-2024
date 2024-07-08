package com.yasunov.shiftappintern.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.yasunov.catalog.navigation.CatalogDest
import com.yasunov.catalog.navigation.PizzaCardDest
import com.yasunov.catalog.ui.CatalogScreen
import com.yasunov.catalog.ui.PizzaCardScreen
import com.yasunov.designsystem.component.ShiftScaffold


@Composable
fun ShiftApp(
    modifier: Modifier = Modifier
) {

    ShiftScaffold(
        bottomBar = {
            BottomBar(modifier)
        }
    ) { padding ->
        val navController = rememberNavController()

        Column(modifier.padding(padding)) {
            NavHost(navController = navController, startDestination = CatalogDest) {
                composable<CatalogDest> {
                    CatalogScreen(
                        navigateOnClick = { id ->
                            navController.navigate(PizzaCardDest(id = id))
                        }
                    )
                }
                composable<PizzaCardDest> { backStackEntry ->
                    val pizzaCardDest: PizzaCardDest = backStackEntry.toRoute()
                    PizzaCardScreen(
                        id = pizzaCardDest.id,
                        onBackIconClicked = { navController.popBackStack() },
                        onButtonNextClicked = {
                            navController.navigate(CatalogDest)
                        }
                    )
                }
            }
        }

    }


}

