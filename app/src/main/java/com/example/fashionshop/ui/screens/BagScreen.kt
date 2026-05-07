package com.example.fashionshop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fashionshop.model.ClothingItem
import com.example.fashionshop.ui.components.BagRowItem
import com.example.fashionshop.viewmodel.ShopUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BagScreen(
    state: ShopUiState,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onAdd: (ClothingItem) -> Unit,
    onRemove: (ClothingItem) -> Unit,
    onCheckout: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("My Bag", fontWeight = FontWeight.Black, fontSize = 22.sp,
                    color = Color(0xFF1A1A2E))
                Surface(shape = RoundedCornerShape(50), color = Color(0xFFF0F0F5)) {
                    Text(
                        "${state.bagCount} item${if (state.bagCount == 1) "" else "s"}",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                        fontSize = 12.sp, color = Color(0xFF9B9BAE), fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.bagIsEmpty) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(180.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🛍️", fontSize = 48.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Your bag is empty", fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1A1A2E), fontSize = 16.sp)
                        Text("Add something nice!", color = Color(0xFF9B9BAE), fontSize = 13.sp)
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f, fill = false)) {
                    items(state.bag.entries.toList(), key = { it.key.id }) { (item, qty) ->
                        BagRowItem(
                            item = item, quantity = qty,
                            onAdd = { onAdd(item) },
                            onRemove = { onRemove(item) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFF7F7FB),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Subtotal", color = Color(0xFF9B9BAE), fontSize = 14.sp)
                            Text("$${String.format("%.2f", state.bagTotal)}",
                                fontSize = 14.sp, color = Color(0xFF1A1A2E))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Shipping", color = Color(0xFF9B9BAE), fontSize = 14.sp)
                            Text("FREE", fontSize = 14.sp, color = Color(0xFF4CAF50),
                                fontWeight = FontWeight.SemiBold)
                        }
                        HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp),
                            color = Color(0xFFE0E0EA))
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total", fontWeight = FontWeight.Bold, fontSize = 16.sp,
                                color = Color(0xFF1A1A2E))
                            Text("$${String.format("%.2f", state.bagTotal)}",
                                fontWeight = FontWeight.Black, fontSize = 18.sp,
                                color = Color(0xFF1A1A2E))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onCheckout,
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1A2E))
                ) {
                    Text("Checkout  ·  $${String.format("%.2f", state.bagTotal)}",
                        fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}