package com.example.caffeine


data class CoffeeType(
    val name: String,
    val image: Int,
)


val coffeeTypes = listOf(
    CoffeeType(
        name = "Macchiato", image = R.drawable.coffee_cup_macchiato
    ), CoffeeType(
        name = "Espresso", image = R.drawable.coffee_cup_espresso
    ), CoffeeType(
        name = "Latte", image = R.drawable.coffee_cup_latte
    ), CoffeeType(
        name = "Black", image = R.drawable.coffee_cup_black
    )
)
val snacks = listOf(
    R.drawable.snack1,
    R.drawable.snack2,
    R.drawable.snack3,
    R.drawable.snack4,
    R.drawable.snack5,
    R.drawable.snack6,
)