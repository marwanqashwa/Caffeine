package com.example.caffeine.screen.homeshowcoffeetypes

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.homeselectcoffeeorder.navigateToHomeSelectCoffeeOrderScreen

fun NavGraphBuilder.homeShowCoffeeTypesRoute(navController: NavController) {
    composable(
        route = Screen.HomeShowCoffeeTypes.route,
        exitTransition = {
            slideOutOfContainer(SlideDirection.Left, tween(1000))+fadeOut(animationSpec = tween(1000))
        },
    ) {
        HomeShowCoffeeTypes(
            onContinueClick = {
                navController.navigateToHomeSelectCoffeeOrderScreen(it)
            }
        )
    }
}


fun NavController.navigateToHomeShowCoffeeTypesScreen() {
    navigate(Screen.HomeShowCoffeeTypes.route)
}