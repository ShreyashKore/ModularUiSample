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
import com.shreyashkore.modularuisample.R
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import com.shreyashkore.modularuisample.ui.SimpleTopBar

@Composable
fun ScannerScreen() {
    val viewModel = viewModel<ScannerViewModel>()

    ScannerScreen(
        scanNewBook = viewModel::scanBook,
        removeBook = viewModel::removeBook,
        openBookDetail = {},
        booksScanned = viewModel.booksScanned.collectAsState().value,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(
    scanNewBook: (book: Book) -> Unit,
    removeBook: (book: Book) -> Unit,
    openBookDetail: () -> Unit,
    booksScanned: List<Book>,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(title = "Scanner")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scanNewBook(SAMPLE_BOOKS.random())
                }
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
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
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Box(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(4.dp)) {
                    Text(
                        text = book.title,
                    )
                    Text(
                        text = book.author,
                    )
                }

                IconButton(
                    onClick = {
                        removeBook(book)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
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
//    AuditScreen(
//
//    )
}

data class BookWithQuantity(
    val book: Book,
    val quantity: Int
)