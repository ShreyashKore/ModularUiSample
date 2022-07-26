package com.shreyashkore.modularuisample.showcase

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shreyashkore.modularuisample.R
import com.shreyashkore.modularuisample.cart.AddToCart
import com.shreyashkore.modularuisample.cart.CartButton
import com.shreyashkore.modularuisample.cart.CartButtonWithCount
import com.shreyashkore.modularuisample.cart.CartUi
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.navigation.Screen
import com.shreyashkore.modularuisample.ui.SimpleTopBar

class Showcase

@Composable
fun Showcase.ShowcaseIcon() {

}


@Composable
fun ShowcaseScreen(
    navController: NavController,
    cartUi: CartUi?,
) {
    val viewModel = viewModel<ShowcaseViewModel>()

    ShowcaseScreen(
        openDetails = {
            navController.navigate(Screen.BookDetails.withArguments(it))
        },
        navigateToCart = {
            navController.navigate(Screen.Cart.route)
        },
        addToCart = viewModel::addToCart,
        books = viewModel.books.collectAsState(emptyList()).value,
        cartBooksCount = viewModel.cartBookCount.collectAsState(0).value,
        cartUi = cartUi,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseScreen(
    openDetails: (id: Long) -> Unit,
    navigateToCart: () -> Unit,
    addToCart: (book: Book) -> Unit,
    books: List<Book>,
    cartBooksCount: Int,
    cartUi: CartUi?,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Showcase",
            ) {
                cartUi?.CartButtonWithCount(
                    onClick = navigateToCart,
                    count = cartBooksCount
                )
            }
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            columns = GridCells.Adaptive(150.dp)
        ) {
            itemsIndexed(books) { i, book ->
                ShowcaseItem(
                    onClick = { openDetails(book.id) },
                    addToCart = addToCart,
                    book = book,
                    cartUi = cartUi
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseItem(
    onClick: () -> Unit,
    addToCart: (book: Book) -> Unit,
    book: Book,
    cartUi: CartUi?
) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = book.title
            )
            cartUi?.AddToCart(
                onClick = { addToCart(book) }
            )
        }
    }
}


@Preview
@Composable
private fun ShowcaseScreenPreview() {
//    AuditScreen(
//
//    )
}