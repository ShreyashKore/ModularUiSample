package com.shreyashkore.modularuisample.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyashkore.modularuisample.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToTile: (route: String) -> Unit,
    screens: List<Screen>,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Home",
                navigationIcon = { /*TODO*/ }
            ) { }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(innerPadding),
            columns = GridCells.Adaptive(minSize = 120.dp)
        ) {
            itemsIndexed(screens) { i, screen ->
                HomeTile(
                    onClick = { navigateToTile(screen.route) },
                    title = screen.title,
                    icon = screen.icon
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTile(
    onClick: () -> Unit,
    title: String,
    icon: ImageVector,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(200.dp),
            )
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
//    AuditScreen(
//
//    )
}
