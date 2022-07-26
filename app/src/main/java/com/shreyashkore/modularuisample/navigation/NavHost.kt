package com.shreyashkore.modularuisample.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shreyashkore.modularuisample.audit.AuditScreen
import com.shreyashkore.modularuisample.cart.CartScreen
import com.shreyashkore.modularuisample.cart.CartUi
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.BookRepository
import com.shreyashkore.modularuisample.data.BookRepositoryMock
import com.shreyashkore.modularuisample.scanner.ScannerScreen
import com.shreyashkore.modularuisample.showcase.BookDetailsScreen
import com.shreyashkore.modularuisample.showcase.ShowcaseScreen
import com.shreyashkore.modularuisample.ui.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    navItems: List<Screen>,
    cartUi: CartUi?
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToTile = { route -> navController.navigate(route) },
                screens = navItems
            )
        }

        composable(Screen.Showcase.route) {
            ShowcaseScreen(
                navController = navController,
                cartUi = cartUi
            )
        }

        composable(Screen.Cart.route) {
            CartScreen()
        }

        composable(Screen.Scanner.route) {
            ScannerScreen()
        }

        composable(Screen.Audit.route) {
            AuditScreen()
        }

        composable(
            Screen.BookDetails.route + "/" + "{book_id}",
            arguments = listOf(navArgument("book_id") { type = NavType.LongType })
        ) {
            val id = it.arguments!!.getLong("book_id")
            var book by remember { mutableStateOf<Book?>(null) }
            LaunchedEffect(key1 = id) {
                book = BookRepositoryMock.getBook(id)
            }
            BookDetailsScreen(book = book)
        }
    }
}