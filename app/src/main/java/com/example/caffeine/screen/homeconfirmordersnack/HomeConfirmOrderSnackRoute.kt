package com.example.caffeine.screen.homeconfirmordersnack


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.homeonboarding.navigateToHomeOnBoardingScreen


fun NavGraphBuilder.homeConfirmOrderSnackRoute(navController: NavController) {
    composable(
        route = Screen.HomeConfirmOrderSnack.route,
        arguments = listOf(
            navArgument("snackID") { type = NavType.IntType }
        ),
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
        val snackID = backStackEntry.arguments?.getInt("snackID") ?: 0

        HomeConfirmOrderSnack(
            snackID = snackID, onConfirmClick = {
                navController.popBackStack(Screen.HomeOnBoarding.route, inclusive = false)
            },onCloseClick = {
                navController.popBackStack(Screen.HomeOnBoarding.route, inclusive = false)
            })
    }
}


fun NavController.navigateToHomeConfirmOrderSnackScreen(snackID: Int) {
    navigate(Screen.HomeConfirmOrderSnack.createRoute(snackID))
}
