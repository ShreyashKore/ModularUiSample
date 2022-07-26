package com.shreyashkore.modularuisample.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository = CartRepositoryMock,
) : ViewModel() {

    val items: Flow<List<Book>> = cartRepository.getObservableCart().map {
        it.books
    }

    fun checkout() {

    }

    fun deleteItem(book: Book) {
        viewModelScope.launch {
            cartRepository.removeBook(book)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            cartRepository.clearAll()
        }
    }
}