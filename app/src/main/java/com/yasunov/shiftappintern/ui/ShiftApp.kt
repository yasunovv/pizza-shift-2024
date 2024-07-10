package com.yasunov.shiftappintern.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.yasunov.cart.navigation.CartDest
import com.yasunov.cart.ui.CartScreen
import com.yasunov.catalog.navigation.CatalogDest
import com.yasunov.catalog.navigation.PizzaCardDest
import com.yasunov.catalog.ui.CatalogScreen
import com.yasunov.catalog.ui.PizzaCardScreen
import com.yasunov.designsystem.component.ShiftScaffold
import com.yasunov.temp.navigation.OrdersDest
import com.yasunov.temp.screen.OrdersScreen


@Composable
fun ShiftApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    ShiftScaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                modifier = modifier
            )
        }
    ) { padding ->


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
                            navController.navigate(CartDest)
                        }
                    )
                }
                composable<CartDest> {
                    CartScreen(onBackIconClicked = { navController.popBackStack() })
                }
                composable<OrdersDest> {
                    OrdersScreen()
                }



            }
        }

    }


}

