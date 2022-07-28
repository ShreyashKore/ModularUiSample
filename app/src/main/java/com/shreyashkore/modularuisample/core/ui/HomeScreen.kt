package com.shreyashkore.modularuisample.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shreyashkore.modularuisample.navigation.SAMPLE_SCREEN_LIST
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
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
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
        modifier = Modifier,
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .size(220.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            )
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navigateToTile = { },
        screens = SAMPLE_SCREEN_LIST
    )
}
