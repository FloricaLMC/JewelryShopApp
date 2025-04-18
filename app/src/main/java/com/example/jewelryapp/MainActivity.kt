package com.example.jewelryapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jewelryapp.components.BottomNavBar
import com.example.jewelryapp.components.TopNavBar
import com.example.jewelryapp.model.Product
import com.example.jewelryapp.navigation.Routes
import com.example.jewelryapp.pages.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            var wishlist by remember { mutableStateOf<List<Product>>(emptyList()) }
            var currentProduct by remember { mutableStateOf<Product?>(null) }
            var cart by remember { mutableStateOf<List<Product>>(emptyList()) }
            var hasFirstOrderDiscount by remember { mutableStateOf(true) }


            NavHost(
                navController = navController,
                startDestination = Routes.LOGIN
            ) {
                composable(Routes.LOGIN) {
                    LoginScreen(navController)
                }

                composable(Routes.HOME) {
                    Scaffold(
                        topBar = {
                            TopNavBar(
                                isLoggedIn = true,
                                onWishlistClick = {
                                    navController.navigate(Routes.WISHLIST)
                                }
                            )
                        },
                        bottomBar = {
                            BottomNavBar(currentScreen = "home") { selected ->
                                navController.navigate(selected)
                            }
                        }
                    ) { padding ->
                        HomeScreen(
                            navController = navController,
                            isLoggedIn = true,
                            onAddToWishlist = { product ->
                                wishlist = wishlist + product
                                Toast.makeText(
                                    context,
                                    "${product.name} added to wishlist",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            onAddToCart = { product ->
                                cart = cart + product
                                Toast.makeText(
                                    context,
                                    "${product.name} added to cart",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            onProductClick = { product ->
                                currentProduct = product
                                navController.navigate(Routes.PRODUCT_DETAILS)
                            },
                            modifier = Modifier.padding(padding)
                        )
                    }
                }

                composable(Routes.SHOP) {
                    Scaffold(
                        topBar = {
                            TopNavBar(
                                isLoggedIn = true,
                                onWishlistClick = {
                                    navController.navigate(Routes.WISHLIST)
                                }
                            )
                        },
                        bottomBar = {
                            BottomNavBar(currentScreen = "shop") { selected ->
                                navController.navigate(selected)
                            }
                        }
                    ) { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            ShopScreen(
                                onAddToCart = { product -> cart = cart + product },
                                onAddToWishlist = { product -> wishlist = wishlist + product },
                                onProductClick = { product ->
                                    currentProduct = product
                                    navController.navigate(Routes.PRODUCT_DETAILS)
                                }
                            )
                        }
                    }
                }

                composable(Routes.CART) {
                    Scaffold(
                        topBar = {
                            TopNavBar(
                                isLoggedIn = true,
                                onWishlistClick = {
                                    navController.navigate(Routes.WISHLIST)
                                }
                            )
                        },
                        bottomBar = {
                            BottomNavBar(currentScreen = "cart") { selected ->
                                navController.navigate(selected)
                            }
                        }
                    ) { padding ->
                        CartScreen(
                            cartItems = cart,
                            navController = navController,
                            onRemoveFromCart = { product -> cart = cart - product },
                            onProceedToCheckout = { navController.navigate(Routes.CHECKOUT) },
                            modifier = Modifier.padding(padding)
                        )
                    }
                }

                composable(Routes.PROFILE) {
                    val sampleUser = User(
                        name = "Lovely",
                        email = "lovely@example.com",
                        address = "456 Jewelry Ave",
                        country = "Canada",
                        currency = "CA",
                        language = "English",
                        paymentMethod = "Credit Card"
                    )

                    Scaffold(
                        topBar = {
                            TopNavBar(
                                isLoggedIn = true,
                                onWishlistClick = {
                                    navController.navigate(Routes.WISHLIST)
                                }
                            )
                        },
                        bottomBar = {
                            BottomNavBar(currentScreen = "profile") { selected ->
                                navController.navigate(selected)
                            }
                        }
                    ) { paddingValues ->
                        ProfileScreen(
                            modifier = Modifier.padding(paddingValues),
                            navController = navController,
                            user = sampleUser,
                            onNavigateToWishlist = { navController.navigate(Routes.WISHLIST) },
                            onNavigateToSettings = { navController.navigate(Routes.SETTINGS) },
                            onLogOut = {
                                navController.navigate(Routes.LOGIN) {
                                    popUpTo(Routes.HOME) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }


                composable(Routes.WISHLIST) {
                    Scaffold(
                        topBar = {
                            TopNavBar(
                                isLoggedIn = true,
                                onWishlistClick = { }
                            )
                        },
                        bottomBar = {
                            BottomNavBar(currentScreen = "wishlist") { selected ->
                                navController.navigate(selected)
                            }
                        }
                    ) { padding ->
                        WishlistScreen(
                            isLoggedIn = true,
                            wishlist = wishlist,
                            onProductClick = { product ->
                                currentProduct = product
                                navController.navigate(Routes.PRODUCT_DETAILS)
                            },
                            onBackClick = {
                                navController.popBackStack()
                            },
                            modifier = Modifier.padding(padding)
                        )
                    }
                }

                composable(Routes.PRODUCT_DETAILS) {
                    currentProduct?.let { product ->
                        ProductDetailsScreen(
                            product = product,
                            onBackClick = { navController.popBackStack() },
                            onAddToWishlist = {
                                wishlist = wishlist + product
                                Toast.makeText(context, "${product.name} added to wishlist", Toast.LENGTH_SHORT).show()
                            },
                            onAddToCart = {
                                cart = cart + product
                                Toast.makeText(context, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }

                composable(Routes.REGISTER) {
                    RegisterScreen(navController)
                }

                composable(Routes.CHECKOUT) {
                    CheckoutScreen(navController)
                }

                composable(Routes.PAYMENT) {
                    PaymentScreen(navController)
                }

                composable(Routes.CONFIRMATION) {
                    ConfirmationScreen()
                }

                composable(Routes.SETTINGS) {
                    Scaffold(
                        topBar = {
                            TopNavBar(
                                isLoggedIn = true,
                                onWishlistClick = {
                                    navController.navigate(Routes.WISHLIST)
                                }
                            )
                        },
                        bottomBar = {
                            BottomNavBar(currentScreen = "profile") { selected ->
                                navController.navigate(selected)
                            }
                        }
                    ) { innerPadding ->
                        SettingsScreen(
                            user = User(
                                "Lovely",
                                "lovely@example.com",
                                "123 Street",
                                "USA",
                                "USD",
                                "English",
                                "Credit Card"
                            ),
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}