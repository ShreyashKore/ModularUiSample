package com.shreyashkore.modularuisample.showcase

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.shreyashkore.modularuisample.R
import com.shreyashkore.modularuisample.cart.*
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import com.shreyashkore.modularuisample.navigation.Screen
import com.shreyashkore.modularuisample.core.ui.SimpleTopBar
import com.shreyashkore.modularuisample.core.ui.theme.ModularUiSampleTheme

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
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
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
    cartUi: CartUi?,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .height(300.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                AsyncImage(
                    model = "file:///android_asset/" + book.imageUri,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    modifier = Modifier
                        .fillMaxSize()
                )
                cartUi?.AddToCartFilled(
                    onClick = { addToCart(book) },
                    modifier = Modifier.align(Alignment.BottomEnd),
                    colors = IconButtonDefaults.filledIconButtonColors()
                )
            }
            Column(
                modifier = Modifier
                    .heightIn(80.dp)
                    .padding(8.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = book.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(4.dp)
                )
                Text(
                    text = book.author,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(4.dp)
                )
            }

        }
    }
}


@Preview
@Composable
private fun ShowcaseScreenPreview() {
    ModularUiSampleTheme {
        ShowcaseScreen(
            openDetails = { },
            navigateToCart = { },
            addToCart = { },
            books = SAMPLE_BOOKS,
            cartBooksCount = 40,
            cartUi = CartUi
        )
    }
}