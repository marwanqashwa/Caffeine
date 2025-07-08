package com.example.caffeine.screen.hometakesnack

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.caffeine.navigation.Screen
import com.example.caffeine.screen.homeconfirmordersnack.navigateToHomeConfirmOrderSnackScreen

fun NavGraphBuilder.homeTakeSnackRoute(navController: NavController) {
    composable(
        route = Screen.HomeTakeSnack.route,
        enterTransition = {
            fadeIn(animationSpec = tween(1000))
        },
        exitTransition = {
            shrinkOut(animationSpec = tween(1000))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(1000))
        },
        popExitTransition = {
            shrinkOut(animationSpec = tween(1000))
        },
    ) {
        HomeTakeSnack(
            onCloseClick = {
                navController.popBackStack(Screen.HomeOnBoarding.route, inclusive = false)
            },
            onSnackClick = {
            navController.navigateToHomeConfirmOrderSnackScreen(it)
        })
    }
}


fun NavController.navigateToHomeTakeSnackScreen() {
    navigate(Screen.HomeTakeSnack.route)
}