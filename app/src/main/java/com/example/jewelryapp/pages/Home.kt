package com.example.jewelryapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jewelryapp.R
import com.example.jewelryapp.model.Product

@Composable
fun HomeScreen(
    navController: NavController,
    isLoggedIn: Boolean,
    onAddToWishlist: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    // lists of products
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


    var selectedCategory by remember { mutableStateOf("male") }
    var maleNecklaceIndex by remember { mutableStateOf(0) }
    var maleRingIndex by remember { mutableStateOf(0) }
    var femaleNecklaceIndex by remember { mutableStateOf(0) }
    var femaleRingIndex by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Gender buttons
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { selectedCategory = "male" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == "male")
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Men")
                }
                Button(
                    onClick = { selectedCategory = "female" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCategory == "female")
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Women")
                }
            }
        }

        // Necklace Section
        item {
            Text(
                text = "Necklaces",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
            )
        }

        item {
            val necklaceProduct = if (selectedCategory == "male") {
                maleNecklaces[maleNecklaceIndex]
            } else {
                femaleNecklaces[femaleNecklaceIndex]
            }

            ProductCard(
                product = necklaceProduct,
                onNext = {
                    if (selectedCategory == "male") {
                        maleNecklaceIndex = (maleNecklaceIndex + 1) % maleNecklaces.size
                    } else {
                        femaleNecklaceIndex = (femaleNecklaceIndex + 1) % femaleNecklaces.size
                    }
                },
                onAddToWishlist = { onAddToWishlist(necklaceProduct) },
                onAddToCart = { onAddToCart(necklaceProduct) },
                onProductClick = {
                    navController.navigate("productDetail/${necklaceProduct.name}/${necklaceProduct.imageRes}")
                }
            )
        }

        // Ring Section
        item {
            Text(
                text = "Rings",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            )
        }

        item {
            val ringProduct = if (selectedCategory == "male") {
                maleRings[maleRingIndex]
            } else {
                femaleRings[femaleRingIndex]
            }

            ProductCard(
                product = ringProduct,
                onNext = {
                    if (selectedCategory == "male") {
                        maleRingIndex = (maleRingIndex + 1) % maleRings.size
                    } else {
                        femaleRingIndex = (femaleRingIndex + 1) % femaleRings.size
                    }
                },
                onAddToWishlist = { onAddToWishlist(ringProduct) },
                onAddToCart = { onAddToCart(ringProduct) },
                onProductClick = {
                    navController.navigate("productDetail/${ringProduct.name}/${ringProduct.imageRes}")
                }
            )
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onNext: () -> Unit,
    onAddToWishlist: () -> Unit,
    onAddToCart: () -> Unit,
    onProductClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onProductClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Name of the product
            Text(product.name, style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.height(4.dp))

            // Price of the product
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(8.dp))

            // Image of the product
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clickable { onProductClick() }
            )

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onAddToWishlist) {
                    Text("Add to Wishlist")
                }
                Button(onClick = onAddToCart) {
                    Text("Add to Cart")
                }
                Button(onClick = onNext) {
                    Text("Next")
                }
            }
        }
    }
}
