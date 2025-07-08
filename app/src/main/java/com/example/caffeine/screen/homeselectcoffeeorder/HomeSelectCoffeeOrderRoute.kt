package com.example.caffeine.screen.homeselectcoffeeorder

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.homeloadingorder.navigateToHomeLoadingOrderScreen


fun NavGraphBuilder.homeSelectCoffeeOrderRoute(navController: NavController) {
    val viewModel = HomeSelectCoffeeOrderViewModel()
    composable(
        route = Screen.HomeSelectCoffeeOrder.route,
        enterTransition = {
            fadeIn(animationSpec = tween(1000))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(500))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(500))
        },

        ) { backStackEntry ->
        val coffeeType = backStackEntry.arguments?.getString("coffeeType") ?: "Unknown"
        HomeSelectCoffeeOrderScreen(
            coffeeTypeTitle = coffeeType,
            onBackClick = {
                navController.popBackStack()
            },
            viewModel = viewModel,
            onContinueClick = {
            navController.navigateToHomeLoadingOrderScreen(it)
        })
    }
}


fun NavController.navigateToHomeSelectCoffeeOrderScreen(coffeeType: String) {
    navigate(Screen.HomeSelectCoffeeOrder.createRoute(coffeeType))
}
