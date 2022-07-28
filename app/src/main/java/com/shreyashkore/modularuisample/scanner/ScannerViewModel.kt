package com.shreyashkore.modularuisample.scanner

import androidx.lifecycle.ViewModel
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import kotlinx.coroutines.flow.MutableStateFlow

class ScannerViewModel : ViewModel(

) {

    val booksScanned: MutableStateFlow<List<Book>> = MutableStateFlow(
        SAMPLE_BOOKS.subList(0, 2)
    )

    fun scanBook(book: Book) {
        booksScanned.value = booksScanned.value + book
    }

    fun removeBook(book: Book) {
        booksScanned.value = booksScanned.value - book
    }

    fun clearAll() {
        booksScanned.value = emptyList()
    }

    fun makeCopy(book: Book) {

    }
}


