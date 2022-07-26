package com.shreyashkore.modularuisample.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuantitySelector(
    currentQuantity: Int,
    onChange: (Int) -> Unit
) {
    OutlinedTextField(
        value = "$currentQuantity",
        onValueChange = { onChange(it.toInt()) },
        leadingIcon = {
            IconButton(onClick = { onChange(currentQuantity - 1) }) {
                Icon(imageVector = Icons.Rounded.Remove, contentDescription = "Remove")
            }
        },
        trailingIcon = {
            IconButton(onClick = { onChange(currentQuantity + 1) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add")
            }
        },
        textStyle  = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
        modifier = Modifier.height(16.dp).wrapContentWidth(),
        shape = RoundedCornerShape(8.dp)
    )
}