package com.example.jewelryapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jewelryapp.R
import com.example.jewelryapp.model.Product

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    onAddToCart: (Product) -> Unit,
    onAddToWishlist: (Product) -> Unit,
    onProductClick: (Product) -> Unit
) {
    // Product lists
    val maleNecklaces = listOf(
        Product("Men's Necklace 1", R.drawable.m_necklace1, 199.99, "Mens Necklace"),
        Product("Men's Necklace 2", R.drawable.m_necklace2, 149.99, "Mens Necklace")
    )
    val maleRings = listOf(
        Product("Men's Ring 1", R.drawable.m_ring1, 99.99, "Mens Rings"),
        Product("Men's Ring 2", R.drawable.m_ring2, 129.99, "Mens Rings")
    )
    val femaleNecklaces = listOf(
        Product("Women's Necklace 1", R.drawable.w_necklace1, 179.99, "Womens Necklace"),
        Product("Women's Necklace 2", R.drawable.w_necklace2, 159.99, "Womens Necklace")
    )
    val femaleRings = listOf(
        Product("Women's Ring 1", R.drawable.w_ring1, 89.99, "Womens Rings"),
        Product("Women's Ring 2", R.drawable.w_ring2, 109.99, "Womens Rings")
    )

    var selectedCategory by remember { mutableStateOf("All") }

    // Filter products based on selected category
    val displayedProducts = remember(selectedCategory) {
        when (selectedCategory) {
            "Necklace" -> femaleNecklaces
            "Rings"   -> femaleRings
            "Necklace"  -> maleNecklaces
            "Rings"     -> maleRings
            "Womens"         -> femaleNecklaces + femaleRings
            "Mens"           -> maleNecklaces + maleRings
            else              -> femaleNecklaces + femaleRings + maleNecklaces + maleRings
        }
    }

    Row(modifier = Modifier.fillMaxSize()) {
        // Left sidebar
        LazyColumn(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                CategoryButton("All", selectedCategory) { selectedCategory = it }
            }
            item {
                CategorySection("Womens")
            }
            item {
                CategoryButton("Necklace", selectedCategory) { selectedCategory = it }
            }
            item {
                CategoryButton("Rings", selectedCategory) { selectedCategory = it }
            }
            item {
                CategorySection("Mens")
            }
            item {
                CategoryButton("Necklace", selectedCategory) { selectedCategory = it }
            }
            item {
                CategoryButton("Rings", selectedCategory) { selectedCategory = it }
            }
        }

        //product display area
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(displayedProducts) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onProductClick(product) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = product.imageRes),
                            contentDescription = product.name,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(product.name, style = MaterialTheme.typography.titleMedium)
                            Text("$${product.price}", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategorySection(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 4.dp)
    )
}

@Composable
fun CategoryButton(
    category: String,
    selectedCategory: String,
    onClick: (String) -> Unit
) {
    val isSelected = category == selectedCategory
    Text(
        text = category.removeSuffix(" Necklace").removeSuffix(" Rings"),
        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(category) }
    )
}
