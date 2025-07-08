package com.example.caffeine.navigation


sealed class Screen(val route: String) {
    object HomeOnBoarding : Screen("home_onboarding")
    object HomeShowCoffeeTypes : Screen("home_show_coffee_types")
    object HomeSelectCoffeeOrder : Screen("home_select_coffee_order/{coffeeType}") {
        fun createRoute(coffeeType: String): String =
            "home_select_coffee_order/${coffeeType}"
    }
    object HomeLoadingOrder : Screen("home_loading_order/{cupSize}"){
        fun createRoute(cupSize: Int): String =
            "home_loading_order/${cupSize}"
    }
    object HomeCoffeReady : Screen("home_coffee_ready")
    object HomeTakeSnack: Screen("home_take_snack")
    object HomeConfirmOrderSnack: Screen("home_confirm_order_snack/{snackID}"){
        fun createRoute(snackID: Int): String =
            "home_confirm_order_snack/${snackID}"
    }
}
