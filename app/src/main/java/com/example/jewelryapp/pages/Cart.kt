package com.example.jewelryapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jewelryapp.model.Product

@Composable
fun CartScreen(
    cartItems: List<Product>,
    navController: NavHostController,
    onRemoveFromCart: (Product) -> Unit,
    onProceedToCheckout: () -> Unit,
    modifier: Modifier = Modifier
) {
    var couponApplied by remember { mutableStateOf(false) }

    // Calculate the subtotal and discount based on couponApplied
    val subtotal = cartItems.sumOf { it.price }
    val discount = if (couponApplied) subtotal * 0.20 else 0.0
    val total = subtotal - discount

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Your Cart", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Text("Your cart is empty.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems.size) { index ->
                    val product = cartItems[index]
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Image(
                                painter = painterResource(id = product.imageRes),
                                contentDescription = product.name,
                                modifier = Modifier
                                    .size(64.dp)
                            )

                            Column(modifier = Modifier.weight(1f)) {
                                Text(product.name, style = MaterialTheme.typography.titleMedium)
                                Text("Price: $${product.price}", style = MaterialTheme.typography.bodyMedium)
                            }

                            Button(onClick = { onRemoveFromCart(product) }) {
                                Text("Remove")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Total price in the cart
            Text("Subtotal: $${"%.2f".format(subtotal)}")

            // Display the discount
            if (couponApplied) {
                Text("First Order Discount: -$${"%.2f".format(discount)}", color = MaterialTheme.colorScheme.primary)
            }

            Text("Total: $${"%.2f".format(total)}", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(12.dp))

            // checkbox to apply the discount
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Apply 20% off",
                    modifier = Modifier.weight(1f)
                )
                Checkbox(
                    checked = couponApplied,
                    onCheckedChange = { couponApplied = it },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onProceedToCheckout,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Proceed to Checkout")
            }
        }
    }
}
