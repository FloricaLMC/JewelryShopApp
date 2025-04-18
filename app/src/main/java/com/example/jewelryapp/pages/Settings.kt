package com.example.jewelryapp.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Address: ${user.address}")
        Text(text = "Country: ${user.country}")
        Text(text = "Currency: ${user.currency}")
        Text(text = "Language: ${user.language}")
        Text(text = "Payment Method: ${user.paymentMethod}")

        Spacer(modifier = Modifier.height(24.dp))
    }
}
