package com.example.caffeine.screen.homeonboarding


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.homeshowcoffeetypes.navigateToHomeShowCoffeeTypesScreen

fun NavGraphBuilder.homeOnBoardingRoute(navController: NavController) {
    composable(
        route = Screen.HomeOnBoarding.route,
    ) {
        HomeOnBoarding(
            onButtonClick = {
                navController.navigateToHomeShowCoffeeTypesScreen()
            })
    }
}


fun NavController.navigateToHomeOnBoardingScreen() {
    navigate(Screen.HomeOnBoarding.route)
}