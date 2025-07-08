package com.example.caffeine.screen.homeloadingorder

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.homecoffeready.navigateToHomeCoffeReadyScreen


fun NavGraphBuilder.homeLoadingOrderRoute(navController: NavController) {
    composable(
        route = Screen.HomeLoadingOrder.route,
        arguments = listOf(
            navArgument("cupSize") { type = NavType.IntType }),
        enterTransition = {
            fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(300))
        },
    ) { backStackEntry ->
        val cupSize = backStackEntry.arguments?.getInt("cupSize") ?: -1
        HomeLoadingOrder(cupSize, onLoadingComplete = {
            navController.navigateToHomeCoffeReadyScreen()
        })
    }
}


fun NavController.navigateToHomeLoadingOrderScreen(cupSize: Int) {
    navigate(Screen.HomeLoadingOrder.createRoute(cupSize))
}


