package com.shreyashkore.modularuisample.audit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import com.shreyashkore.modularuisample.ui.SimpleTopBar

object AuditUi

@Composable
fun AuditScreen() {

    val viewModel = viewModel<AuditViewModel>()

    AuditScreen(
        expectedBooks = viewModel.expectedBooks,
        foundBooks = viewModel.foundBooks.collectAsState().value,
        notFoundBooks = viewModel.notFoundBooks.collectAsState().value,
        changeBookStatus = viewModel::confirmBookStatus,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuditScreen(
    expectedBooks: List<Book>,
    foundBooks: List<Book>,
    notFoundBooks: List<Book>,
    changeBookStatus: (book: Book, available: Boolean) -> Unit,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(title = "Audit")
        }
    ) { innerPadding ->
        val tabTitles = listOf("Expected", "Found", "Not Found")
        var currentTabIndex by remember { mutableStateOf(0) }

        Column(modifier = Modifier.padding(innerPadding)) {

            TabRow(selectedTabIndex = currentTabIndex) {
                tabTitles.forEachIndexed { i, tabTitle ->
                    Tab(
                        selected = currentTabIndex == i,
                        onClick = {
                            currentTabIndex = i
                        },
                        text = {
                            Text(text = tabTitle)
                        },
                    )
                }
            }

            when (currentTabIndex) {
                0 -> {
                    LazyColumn() {
                        itemsIndexed(expectedBooks) { i, book ->
                            AuditRow(
                                book = book,
                                isChecked = null,
                                changeBookStatus = { }
                            )
                        }
                    }
                }
                1 -> {
                    LazyColumn() {
                        itemsIndexed(foundBooks) { i, book ->
                            AuditRow(
                                book = book,
                                isChecked = true,
                                changeBookStatus = { changeBookStatus(book, it) },
                            )
                        }
                    }
                }
                2 -> {
                    LazyColumn() {
                        itemsIndexed(notFoundBooks) { i, book ->
                            AuditRow(
                                book = book,
                                isChecked = false,
                                changeBookStatus = { changeBookStatus(book, it) },
                            )
                        }
                    }
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuditRow(
    book: Book,
    isChecked: Boolean?,
    changeBookStatus: (Boolean) -> Unit,
) {
    Row() {
        Cell(text = book.title, modifier = Modifier.weight(1f))
        Cell(text = book.author, modifier = Modifier.weight(1f))
        isChecked?.let {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    changeBookStatus(it)
                }
            )
        }
    }
}


@Composable
fun Cell(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .padding(12.dp)
    )
}

@Preview
@Composable
private fun AuditScreenPreview() {
    AuditScreen(
        expectedBooks = SAMPLE_BOOKS,
        foundBooks = SAMPLE_BOOKS.subList(1, 3),
        notFoundBooks = SAMPLE_BOOKS.subList(1, 3),
        changeBookStatus = {_, _ ->}
    )
}