package com.shreyashkore.modularuisample.data

import com.shreyashkore.modularuisample.audit.AuditUi
import com.shreyashkore.modularuisample.cart.CartUi
import com.shreyashkore.modularuisample.scanner.ScannerUi

data class UiFeatures(
    val cartUi: CartUi?,
    val auditUi: AuditUi?,
    val scannerUi: ScannerUi?,
) {
}

fun ShopFeatures.toUiFeatures() = UiFeatures(
    cartUi = if (cartEnabled) CartUi else null,
    auditUi = if (auditEnabled) AuditUi else null,
    scannerUi = if (scannerEnabled) ScannerUi else null
)