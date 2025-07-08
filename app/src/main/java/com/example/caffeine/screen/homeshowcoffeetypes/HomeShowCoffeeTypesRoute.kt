package com.example.caffeine.screen.homeshowcoffeetypes


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.homeselectcoffeeorder.navigateToHomeSelectCoffeeOrderScreen

fun NavGraphBuilder.homeShowCoffeeTypesRoute(navController: NavController) {
    composable(
        route = Screen.HomeShowCoffeeTypes.route,
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