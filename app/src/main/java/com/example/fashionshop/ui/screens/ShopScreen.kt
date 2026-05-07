package com.example.fashionshop.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fashionshop.ui.components.BagIcon
import com.example.fashionshop.ui.components.CategoryChip
import com.example.fashionshop.ui.components.ProductCard
import com.example.fashionshop.viewmodel.ShopViewModel

private val categories = listOf("All", "Women", "Men", "Kids")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopScreen(viewModel: ShopViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(containerColor = Color(0xFFF7F7FB)) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("FASHION SHOP", fontWeight = FontWeight.Black, fontSize = 20.sp,
                        letterSpacing = 2.sp, color = Color(0xFF1A1A2E))
                    Text("${state.filteredItems.size} items", fontSize = 12.sp,
                        color = Color(0xFF9B9BAE))
                }
                BagIcon(count = state.bagCount, onClick = { viewModel.toggleBag() })
            }

            LazyRow(
                modifier = Modifier.background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(
                        label = category,
                        selected = state.selectedCategory == category,
                        onClick = { viewModel.selectCategory(category) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(state.filteredItems, key = { it.id }) { item ->
                    ProductCard(item = item, onAddToBag = { viewModel.addToBag(item) })
                }
            }
        }

        if (state.showBag) {
            BagScreen(
                state = state,
                sheetState = sheetState,
                onDismiss = { viewModel.toggleBag() },
                onAdd = { viewModel.addToBag(it) },
                onRemove = { viewModel.removeFromBag(it) },
                onCheckout = { viewModel.clearBag() }
            )
        }
    }
}