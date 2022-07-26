package com.shreyashkore.modularuisample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shreyashkore.modularuisample.data.ShopFeatures
import com.shreyashkore.modularuisample.data.toUiFeatures
import com.shreyashkore.modularuisample.navigation.NavGraph
import com.shreyashkore.modularuisample.navigation.navItems
import com.shreyashkore.modularuisample.ui.theme.ModularUiSampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModularUiSampleTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var shopFeatures by remember {
        mutableStateOf(ShopFeatures(true, true, true))
    }
    val uiFeatures = shopFeatures.toUiFeatures()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            Divider()
            navItems.forEach {
                val backStack by navController.currentBackStackEntryAsState()
                val isSelected = backStack?.destination?.route == it.route
                NavigationDrawerItem(
                    label = { Text(it.title) },
                    selected = isSelected,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(it.route)
                    },
                    icon = { Icon(it.icon, it.title) }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(Modifier.padding(16.dp)) {
                TextWithCheckBox(
                    text = "Cart Features",
                    onCheckChanged = {
                        shopFeatures = shopFeatures.copy(cartEnabled = it)
                    },
                    checked = uiFeatures.cartUi.isNotNull()
                )
                TextWithCheckBox(
                    text = "Audit Features",
                    onCheckChanged = {
                        shopFeatures = shopFeatures.copy(auditEnabled = it)
                    },
                    checked = uiFeatures.auditUi.isNotNull()
                )
                TextWithCheckBox(
                    text = "Scanner Features",
                    onCheckChanged = {
                        shopFeatures = shopFeatures.copy(scannerEnabled = it)
                    },
                    checked = uiFeatures.scannerUi.isNotNull()
                )
            }

        }
    ) {
        Column(modifier = Modifier.padding()) {
            NavGraph(
                navController = navController,
                navItems = navItems,
                cartUi = uiFeatures.cartUi
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextWithCheckBox(
    text: String,
    onCheckChanged: (Boolean) -> Unit,
    checked: Boolean,
    modifier : Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChanged
        )
    }
}

fun Any?.isNotNull(): Boolean = this != null

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModularUiSampleTheme {
//        MainScreen()
    }
}