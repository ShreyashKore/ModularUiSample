package com.shreyashkore.modularuisample.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCartCheckout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shreyashkore.modularuisample.cart.CartUi.ClearAll
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import com.shreyashkore.modularuisample.core.ui.SimpleTopBar
import com.shreyashkore.modularuisample.core.ui.theme.ModularUiSampleTheme

@Composable
fun CartScreen() {
    val viewModel = viewModel<CartViewModel>()

    CartScreen(
        deleteItem = viewModel::deleteItem,
        checkOut = viewModel::checkout,
        clearAll = viewModel::clearAll,
        books = viewModel.items.collectAsState(emptyList()).value,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    deleteItem: (book: Book) -> Unit,
    checkOut: () -> Unit,
    clearAll: () -> Unit,
    books: List<Book>,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Cart",
                actions = {
                    ClearAll(onClick = clearAll)
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Check Out") },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.ShoppingCartCheckout,
                        contentDescription = "Check Out"
                    )
                },
                onClick = checkOut
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                itemsIndexed(books) { i, book ->
                    CartItem(
                        onQuantityChange = { },
                        removeBook = { deleteItem(book) },
                        book = book,
                        quantity = 1
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun CartScreenPreview() {
    ModularUiSampleTheme {
        CartScreen(
            deleteItem = {},
            checkOut = {},
            clearAll = {},
            books = SAMPLE_BOOKS
        )
    }
}



