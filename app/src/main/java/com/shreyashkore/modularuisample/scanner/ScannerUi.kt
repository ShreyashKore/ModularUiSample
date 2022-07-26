package com.shreyashkore.modularuisample.scanner


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.runtime.Composable
import com.shreyashkore.modularuisample.ui.HomeTile

object ScannerUi

@Composable
fun ScannerUi.IconCard() {
    HomeTile(
        onClick = { /*TODO*/ },
        title = "Scanner",
        icon = Icons.Rounded.Create
    )
}

@Composable
fun ScannerUi.NavigationItem() {

}

@Composable
fun ScannerUiScreen() {

}