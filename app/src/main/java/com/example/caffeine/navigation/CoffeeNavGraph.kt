package com.example.caffeine.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.caffeine.screen.homecoffeready.homeCoffeeReadyRoute
import com.example.caffeine.screen.homeconfirmordersnack.homeConfirmOrderSnackRoute
import com.example.caffeine.screen.homeloadingorder.homeLoadingOrderRoute
import com.example.caffeine.screen.homeonboarding.homeOnBoardingRoute
import com.example.caffeine.screen.homeselectcoffeeorder.homeSelectCoffeeOrderRoute
import com.example.caffeine.screen.homeshowcoffeetypes.homeShowCoffeeTypesRoute
import com.example.caffeine.screen.hometakesnack.homeTakeSnackRoute

fun NavGraphBuilder.coffeeNavGraph(navController: NavHostController) {
    homeOnBoardingRoute(navController)
    homeShowCoffeeTypesRoute(navController)
    homeSelectCoffeeOrderRoute(navController)
    homeLoadingOrderRoute(navController)
    homeCoffeeReadyRoute(navController)
    homeTakeSnackRoute(navController)
    homeConfirmOrderSnackRoute(navController)
}