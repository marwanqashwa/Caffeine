package com.example.caffeine.screen.homecoffeready

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.hometakesnack.navigateToHomeTakeSnackScreen

fun NavGraphBuilder.homeCoffeeReadyRoute(navController: NavController) {
    composable(
        route = Screen.HomeCoffeReady.route,
        exitTransition = {
            fadeOut(tween(1100))
        },
        popExitTransition = {
            fadeOut(tween(1100))
        }
    ) {
        HomeCoffeeReady(
            onTakeSnackClick = {
                navController.navigateToHomeTakeSnackScreen()
            }, onCloseClick = {
                navController.popBackStack(Screen.HomeOnBoarding.route, inclusive = false)
            }
        )
    }
}


fun NavController.navigateToHomeCoffeReadyScreen() {
    navigate(Screen.HomeCoffeReady.route)
}