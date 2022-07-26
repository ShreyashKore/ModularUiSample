package com.shreyashkore.modularuisample.showcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyashkore.modularuisample.cart.CartRepository
import com.shreyashkore.modularuisample.cart.CartRepositoryMock
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.BookRepository
import com.shreyashkore.modularuisample.data.BookRepositoryMock
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ShowcaseViewModel(
    private val bookRepository: BookRepository = BookRepositoryMock,
    private val cartRepository: CartRepository = CartRepositoryMock
) : ViewModel() {

    val cartBookCount: Flow<Int> = cartRepository.getCartCount()

    val books: Flow<List<Book>> = bookRepository.getObservableBooks()

    fun addToCart(book: Book) {
        viewModelScope.launch {
            cartRepository.addBookToCart(book)
        }
    }
}

