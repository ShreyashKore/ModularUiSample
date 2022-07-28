package com.shreyashkore.modularuisample.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector



sealed class Screen(
    val title: String,
    val icon: ImageVector,
    val route: String,
) {
    object Home : Screen("Home", Icons.Rounded.Home, "home")
    object Cart : Screen("Cart", Icons.Rounded.ShoppingCart, "shopping_cart")
    object BookDetails : Screen("Book", Icons.Rounded.MenuBook, "book_details") {
        fun withArguments(id: Long): String {
            return route + "/" + id
        }
    }

    object Scanner : Screen("Scanner", Icons.Rounded.Scanner, "scanner")
    object Showcase : Screen("Showcase", Icons.Rounded.Book, "book")
    object Audit : Screen("Audit", Icons.Rounded.Calculate, "audit")
}

val SAMPLE_SCREEN_LIST = listOf(
    Screen.Showcase,
    Screen.Scanner,
    Screen.Audit,
)