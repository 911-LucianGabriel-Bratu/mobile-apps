package com.example.app.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app.model.MusicalInstruments
import com.example.app.service.MusicalInstrumentsService
import com.example.app.service.OrdersService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utils.OrdersCardElement
import utils.ProductsCardElement

@Composable
fun OrdersScreen(ordersService: OrdersService, navController: NavController) {
    val ordersList by remember {
        ordersService.allOrders
    }.collectAsState(initial = emptyList())

    LazyColumn {
        items(ordersList) { order ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrdersCardElement(
                    orderID = order.orderID,
                    orderedAt = order.orderedAt,
                    quantity = order.quantity,
                    totalPrice = order.totalPrice,
                    navController = navController
                )
            }
        }
    }
}