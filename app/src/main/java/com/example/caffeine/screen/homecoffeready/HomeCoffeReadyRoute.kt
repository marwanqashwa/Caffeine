package com.example.caffeine.screen.homecoffeready

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.hometakesnack.navigateToHomeTakeSnackScreen

fun NavGraphBuilder.homeCoffeeReadyRoute(navController: NavController) {
    composable(
        route = Screen.HomeCoffeReady.route,
    ) {
        HomeCoffeeReady(
            onTakeSnackClick = {
                navController.navigateToHomeTakeSnackScreen()
            }, onCloseClick = {
                navController.popBackStack(Screen.HomeSelectCoffeeOrder.route, inclusive = false)
            }
        )
    }
}


fun NavController.navigateToHomeCoffeReadyScreen() {
    navigate(Screen.HomeCoffeReady.route)
}