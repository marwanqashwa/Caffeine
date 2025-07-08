package com.example.caffeine.screen.homeselectcoffeeorder

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeSelectCoffeeOrderViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeSelectCoffeeOrderUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSelectedCupSize(cupSize: CupSize) {
        _uiState.value = _uiState.value.copy(selectedCupSize = cupSize)
    }
    fun updateSelectedCaffeineSize(caffeineSize: CaffeineSize) {
        _uiState.value = _uiState.value.copy(selectedCaffeineSize = caffeineSize)
    }
    fun hideTopBar(){
        _uiState.value = _uiState.value.copy(isTopBarVisible = !_uiState.value.isTopBarVisible)
    }

}