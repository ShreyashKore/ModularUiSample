package com.shreyashkore.modularuisample.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shreyashkore.modularuisample.R
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import com.shreyashkore.modularuisample.core.ui.components.QuantitySelector
import com.shreyashkore.modularuisample.core.ui.components.TextWithIcon
import com.shreyashkore.modularuisample.core.ui.theme.ModularUiSampleTheme

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
fun CartUi.AddToCart(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors()
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
        Icon(
            imageVector = Icons.Rounded.ShoppingCart,
            contentDescription = "Go to CartFeatures"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartUi.AddToCartFilled(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors()
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
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
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            AsyncImage(
                model = "file:///android_asset/" + book.imageUri,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(64.dp).height(54.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = book.title,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextWithIcon(
                        icon = Icons.Rounded.AttachMoney,
                        text = book.price.toString(),
                        textStyle = TextStyle(fontSize = 20.sp)
                    )
                    QuantitySelector(
                        currentQuantity = quantity,
                        onChange = onQuantityChange,
                        modifier = Modifier
                            .wrapContentHeight()
                    )
                }
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

@Preview
@Composable
private fun CartUiPreview() {
    ModularUiSampleTheme {
        CartItem(
            onQuantityChange = { },
            removeBook = { },
            book = SAMPLE_BOOKS[0],
            quantity = 5
        )
    }
}