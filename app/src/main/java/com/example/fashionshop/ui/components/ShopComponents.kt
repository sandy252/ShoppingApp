package com.example.fashionshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fashionshop.model.ClothingItem

@Composable
fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    val bgColor   = if (selected) Color(0xFF1A1A2E) else Color(0xFFF0F0F5)
    val textColor = if (selected) Color.White       else Color(0xFF1A1A2E)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(bgColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 9.dp)
    ) {
        Text(label, color = textColor, fontWeight = FontWeight.Medium, fontSize = 13.sp)
    }
}

@Composable
fun ProductCard(item: ClothingItem, onAddToBag: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth().height(130.dp).background(Color(0xFFF7F7FB)),
                contentAlignment = Alignment.Center
            ) {
                Text(item.emoji, fontSize = 60.sp)
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(item.brand, fontSize = 11.sp, color = Color(0xFF9B9BAE), fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(2.dp))
                Text(item.name, fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                    maxLines = 2, overflow = TextOverflow.Ellipsis, color = Color(0xFF1A1A2E))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("$${String.format("%.2f", item.price)}", fontWeight = FontWeight.Bold,
                        fontSize = 15.sp, color = Color(0xFF1A1A2E))
                    Box(
                        modifier = Modifier.size(32.dp).clip(CircleShape)
                            .background(Color(0xFF1A1A2E)).clickable(onClick = onAddToBag),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add to bag",
                            tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun BagIcon(count: Int, onClick: () -> Unit) {
    Box(modifier = Modifier.clickable(onClick = onClick)) {
        Icon(Icons.Default.ShoppingBag, contentDescription = "Open bag",
            modifier = Modifier.size(28.dp), tint = Color(0xFF1A1A2E))
        if (count > 0) {
            Box(
                modifier = Modifier.align(Alignment.TopEnd).size(16.dp)
                    .clip(CircleShape).background(Color(0xFFE63946)),
                contentAlignment = Alignment.Center
            ) {
                Text(if (count > 9) "9+" else count.toString(),
                    color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun BagRowItem(item: ClothingItem, quantity: Int, onAdd: () -> Unit, onRemove: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF7F7FB)),
            contentAlignment = Alignment.Center
        ) {
            Text(item.emoji, fontSize = 28.sp)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(item.name, fontWeight = FontWeight.SemiBold, fontSize = 13.sp,
                maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color(0xFF1A1A2E))
            Text(item.brand, fontSize = 11.sp, color = Color(0xFF9B9BAE))
            Text("$${String.format("%.2f", item.price * quantity)}",
                fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF1A1A2E))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.border(1.dp, Color(0xFFE0E0EA), RoundedCornerShape(8.dp))
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            IconButton(onClick = onRemove, modifier = Modifier.size(28.dp)) {
                Icon(
                    if (quantity == 1) Icons.Default.Delete else Icons.Default.Remove,
                    contentDescription = "Remove", modifier = Modifier.size(16.dp),
                    tint = if (quantity == 1) Color(0xFFE63946) else Color(0xFF1A1A2E)
                )
            }
            Text(quantity.toString(), fontWeight = FontWeight.Bold,
                modifier = Modifier.widthIn(min = 20.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            IconButton(onClick = onAdd, modifier = Modifier.size(28.dp)) {
                Icon(Icons.Default.Add, contentDescription = "Add", modifier = Modifier.size(16.dp))
            }
        }
    }
    HorizontalDivider(color = Color(0xFFF0F0F5))
}