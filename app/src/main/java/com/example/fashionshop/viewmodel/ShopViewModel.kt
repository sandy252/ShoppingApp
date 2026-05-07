package com.example.fashionshop.viewmodel

import androidx.lifecycle.ViewModel
import com.example.fashionshop.model.ClothingItem
import com.example.fashionshop.model.SampleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ShopUiState(
    val allItems: List<ClothingItem> = SampleData.items,
    val selectedCategory: String = "All",
    val bag: Map<ClothingItem, Int> = emptyMap(),
    val showBag: Boolean = false
) {
    val filteredItems: List<ClothingItem>
        get() = if (selectedCategory == "All") allItems
        else allItems.filter { it.category == selectedCategory }

    val bagCount: Int
        get() = bag.values.sum()

    val bagTotal: Double
        get() = bag.entries.sumOf { (item, qty) -> item.price * qty }

    val bagIsEmpty: Boolean
        get() = bag.isEmpty()
}

class ShopViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ShopUiState())
    val uiState: StateFlow<ShopUiState> = _uiState.asStateFlow()

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun addToBag(item: ClothingItem) {
        _uiState.update { state ->
            val currentQty = state.bag[item] ?: 0
            state.copy(bag = state.bag + (item to currentQty + 1))
        }
    }

    fun removeFromBag(item: ClothingItem) {
        _uiState.update { state ->
            val currentQty = state.bag[item] ?: 0
            val newBag = when {
                currentQty <= 1 -> state.bag - item
                else            -> state.bag + (item to currentQty - 1)
            }
            state.copy(bag = newBag)
        }
    }

    fun toggleBag() {
        _uiState.update { it.copy(showBag = !it.showBag) }
    }

    fun clearBag() {
        _uiState.update { it.copy(bag = emptyMap(), showBag = false) }
    }
}