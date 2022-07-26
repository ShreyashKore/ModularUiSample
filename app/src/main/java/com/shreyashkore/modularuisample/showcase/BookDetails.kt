package com.shreyashkore.modularuisample.showcase

import android.hardware.camera2.CameraManager.AvailabilityCallback
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyashkore.modularuisample.R
import com.shreyashkore.modularuisample.data.Book
import com.shreyashkore.modularuisample.data.SAMPLE_BOOKS
import com.shreyashkore.modularuisample.navigation.Screen
import com.shreyashkore.modularuisample.ui.theme.ModularUiSampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    book: Book?
) {
    Scaffold() {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            if (book == null) {
                return@LazyColumn
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier
                        .heightIn(360.dp)
                        .fillMaxWidth()
                )
            }

            item {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp, 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextWithIcon(
                        text = book.author,
                        icon = Icons.Rounded.Person
                    )
                    RatingBar(
                        value = book.rating,
                        totalStars = 5,
                        highLightColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer
                    )

                }
                Row(
                    modifier = Modifier
                        .padding(16.dp, 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextWithIcon(
                        text = book.price.toString(),
                        icon = Icons.Rounded.AttachMoney
                    )
                    AvailabilityIndicator(
                        available = book.isAvailable
                    )
                }
            }

            item {
                Text(
                    text = book.description,
                    modifier = Modifier.padding(16.dp)
                )
            }

        }

    }

}


@Composable
fun RatingBar(
    value: Number,
    totalStars: Int,
    highLightColor: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val valueInt = value.toInt()
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalStars) {
            val color = if (it <= valueInt) highLightColor else backgroundColor
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = color,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailabilityIndicator(
    available: Boolean,
    modifier: Modifier = Modifier
) {
    val text = if (available) "Available" else "Unavailable"
    AssistChip(
        onClick = { /*TODO*/ },
        label = { Text(text = text) },
        modifier = modifier
    )
}

@Composable
fun TextWithIcon(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun BookDetailsPreview() {
    ModularUiSampleTheme {
        BookDetailsScreen(book = SAMPLE_BOOKS[0])
    }
}