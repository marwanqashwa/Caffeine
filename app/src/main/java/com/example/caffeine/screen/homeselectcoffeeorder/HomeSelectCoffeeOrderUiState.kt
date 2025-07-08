package com.example.caffeine.screen.homeselectcoffeeorder

data class HomeSelectCoffeeOrderUiState(
    val selectedCupSize: CupSize = CupSize.SMALL,
    val selectedCaffeineSize: CaffeineSize = CaffeineSize.LOW,
    val isTopBarVisible: Boolean = true
)

enum class CupSize(val size: Int){
    SMALL(150),
    MEDIUM(200),
    LARGE(400)
}

enum class CaffeineSize(){
    LOW,
    MEDIUM,
    HIGH
}

