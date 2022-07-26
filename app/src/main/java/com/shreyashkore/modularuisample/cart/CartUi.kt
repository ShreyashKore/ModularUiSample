package com.shreyashkore.modularuisample.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shreyashkore.modularuisample.R
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.ui.QuantitySelector

object CartUi {
    @Composable
    fun ClearAll(
        onClick: () -> Unit
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Rounded.ClearAll,
                contentDescription = "Clear All"
            )
        }
    }
}

@Composable
fun CartUi?.CartButtonWithCount(
    onClick: () -> Unit,
    count: Int,
) {
    IconButton(onClick = onClick) {
        BadgedBox(
            badge = {
                if (count > 0)
                    Badge() {
                        Text(text = "$count")
                    }
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = "Clear All"
            )
        }
    }
}

@Composable
fun CartUi.CartButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Rounded.ShoppingCart,
            contentDescription = "Clear All"
        )
    }
}


@Composable
fun CartUi.CheckOut(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Rounded.Check,
            contentDescription = "Clear All"
        )
    }
}

@Composable
fun CartUi.AddToCart(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Rounded.ShoppingCart,
            contentDescription = "Go to CartFeatures"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItem(
    onQuantityChange: (Int) -> Unit,
    removeBook: () -> Unit,
    book: Book,
    quantity: Int,
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )

            Column {
                Text(
                    text = book.title,
                )
                Text(
                    text = book.author,
                )
                QuantitySelector(
                    currentQuantity = quantity,
                    onChange = onQuantityChange,
                )
            }

            IconButton(onClick = removeBook) {
                Icon(
                    imageVector = Icons.Rounded.Cancel,
                    contentDescription = "Remove Book",
                )
            }
        }
    }

}