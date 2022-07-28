package com.shreyashkore.modularuisample.scanner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.shreyashkore.modularuisample.R
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import com.shreyashkore.modularuisample.core.ui.SimpleTopBar
import com.shreyashkore.modularuisample.core.ui.theme.ModularUiSampleTheme

@Composable
fun ScannerScreen() {
    val viewModel = viewModel<ScannerViewModel>()

    ScannerScreen(
        scanNewBook = viewModel::scanBook,
        removeBook = viewModel::removeBook,
        openBookDetail = {},
        clearAll = viewModel::clearAll,
        booksScanned = viewModel.booksScanned.collectAsState().value,
    )



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(
    scanNewBook: (book: Book) -> Unit,
    removeBook: (book: Book) -> Unit,
    openBookDetail: () -> Unit,
    clearAll: () -> Unit,
    booksScanned: List<Book>,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(title = "Scanner") {
                IconButton(onClick = clearAll) {
                    Icon(
                        imageVector = Icons.Rounded.ClearAll,
                        contentDescription = "Clear All"
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scanNewBook(SAMPLE_BOOKS.random())
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(booksScanned) { i, book ->
                ScannedItem(
                    removeBook = removeBook,
                    openBookDetail = openBookDetail,
                    book = book,
                )
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannedItem(
    removeBook: (book: Book) -> Unit,
    openBookDetail: () -> Unit,
    book: Book,
) {
    Card(
        onClick = openBookDetail,
        modifier = Modifier.padding(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = "file:///android_asset/" + book.imageUri,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            
            Spacer(modifier = Modifier.width(10.dp))
            
            Box(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(0.dp)) {
                    Text(
                        text = book.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Text(
                        text = book.author,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }

                IconButton(
                    onClick = { removeBook(book) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Cancel,
                        contentDescription = "Remove"
                    )
                }
            }

        }
    }

}


@Preview
@Composable
private fun ScannerScreenPreview() {
    ModularUiSampleTheme {
        ScannerScreen(
            scanNewBook = {},
            removeBook = {},
            openBookDetail = {},
            clearAll = {},
            booksScanned = SAMPLE_BOOKS
        )
    }
}

data class BookWithQuantity(
    val book: Book,
    val quantity: Int
)