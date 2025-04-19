package com.example.jewelryapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
<<<<<<< HEAD
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jewelryapp.R
=======
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
>>>>>>> 38850c6623d115dfa9ed208df87229fd92d0f602
import com.example.jewelryapp.model.Product

@Composable
fun ProductDetailsScreen(
    product: Product,
    onBackClick: () -> Unit,
    onAddToWishlist: () -> Unit,
    onAddToCart: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Back to Home")
        }

        // Product Name
        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Product Image
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Price
        Text(
            text = "Price: $${product.price}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product Description
        Text(
<<<<<<< HEAD
            text = "Description: LoveD Jewelry: Waterproof, Tarnish-proof, High Quality and Long-Term Use ",
=======
            text = "Description: ",
>>>>>>> 38850c6623d115dfa9ed208df87229fd92d0f602
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onAddToWishlist) {
                Text("Add to Wishlist")
            }
            Button(onClick = onAddToCart) {
                Text("Add to Cart")
            }
        }
<<<<<<< HEAD

        // Rating
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Rating: ", style = MaterialTheme.typography.bodyLarge)
            repeat(5) { index ->
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = if (index < product.averageRating.toInt()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("${product.averageRating}/5")
        }

        // Reviews Title
        Text(
            text = "Customer Reviews:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        // Reviews List
        product.reviews.forEach { review ->
            Text(
                text = "- $review",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

=======
    }
}
>>>>>>> 38850c6623d115dfa9ed208df87229fd92d0f602
