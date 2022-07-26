package com.shreyashkore.modularuisample.audit

import androidx.lifecycle.ViewModel
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.BookRepository
import com.shreyashkore.modularuisample.data.BookRepositoryMock
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import kotlinx.coroutines.flow.MutableStateFlow

class AuditViewModel(
    private val bookRepository: BookRepository = BookRepositoryMock
) : ViewModel() {

    val expectedBooks: List<Book> = SAMPLE_BOOKS

    val foundBooks: MutableStateFlow<List<Book>> = MutableStateFlow(SAMPLE_BOOKS.subList(0, 3))
    val notFoundBooks: MutableStateFlow<List<Book>> = MutableStateFlow(SAMPLE_BOOKS.subList(4, 6))

    fun confirmBookStatus(book: Book, available: Boolean) {
        if (available) {
            foundBooks.value = foundBooks.value + book
            notFoundBooks.value = notFoundBooks.value - book
        } else {
            foundBooks.value = foundBooks.value - book
            notFoundBooks.value = notFoundBooks.value + book
        }
    }

    fun markMissing() {

    }
}