package com.shreyashkore.modularuisample.core.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shreyashkore.modularuisample.core.ui.theme.ModularUiSampleTheme

@SuppressLint("ModifierParameter")
@Composable
fun QuantitySelector(
    currentQuantity: Int,
    onChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(0.5f)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { onChange(currentQuantity - 1) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(imageVector = Icons.Rounded.Remove, contentDescription = "Remove")
            }
            Text(
                text = "$currentQuantity",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                modifier = Modifier.padding(horizontal = 12.dp),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            IconButton(
                onClick = { onChange(currentQuantity - 1) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add")
            }
        }
    }
}

@Preview
@Composable
private fun QuantitySelectorPreview() {
    ModularUiSampleTheme {
        QuantitySelector(currentQuantity = 100, onChange = {})
    }
}