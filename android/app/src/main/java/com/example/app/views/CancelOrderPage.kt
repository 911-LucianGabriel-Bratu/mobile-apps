package com.example.app.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.app.api.RetrofitClient
import com.example.app.model.Orders
import com.example.app.model.PendingOperations
import com.example.app.navigation.Routes
import com.example.app.service.OrdersService
import com.example.app.service.PendingOperationsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CancelOrderPage(ordersService: OrdersService, navController: NavController, pendingOperationsService: PendingOperationsService){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val orderID = navBackStackEntry?.arguments?.getString("data")

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Are you sure you want to cancel this order?",
            color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center,
            fontSize = 48.sp, overflow = TextOverflow.Ellipsis, lineHeight = 50.sp)
        Spacer(Modifier.height(200.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val currentContext = LocalContext.current
            IconButton(
                onClick = {

                    cancelOrder(Integer.parseInt(orderID), ordersService, pendingOperationsService){
                        wasSuccessful ->
                        if(wasSuccessful == "successful"){
                            Toast.makeText(currentContext, "Order cancelled", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        }
                        else if(wasSuccessful == "serverError"){
                            Toast.makeText(currentContext, "Server has encountered an error. Could not cancel order.", Toast.LENGTH_SHORT).show()
                        }
                        else if(wasSuccessful == "connectionError"){
                            Toast.makeText(currentContext, "You or the server may be offline. Order was cancelled locally", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        }
                        else if(wasSuccessful == "nullEntry"){
                            Toast.makeText(currentContext, "Null entry. Should not have happened.", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            )
            {
                Icon(
                    modifier = Modifier.size(100.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Green
                )
            }
            Spacer(Modifier.width(100.dp))
            IconButton(
                onClick = { navController.navigateUp() }
            )
            {
                Icon(
                    modifier = Modifier.size(100.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}

fun cancelOrder(
    orderID: Int,
    ordersService: OrdersService,
    pendingOperationsService: PendingOperationsService,
    callback: (String) -> Unit
){
    CoroutineScope(Dispatchers.Main).launch {
        var wasSuccessful: String
        val order: Orders? = ordersService.getOrderByID(orderID)
        if(order != null){
            val orderApi = RetrofitClient.getOrderApi()

            val call = orderApi.deleteOrder(orderID)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        ordersService.deleteOrder(order)
                        wasSuccessful = "successful"
                    } else {
                        wasSuccessful = "serverError"
                    }
                    callback(wasSuccessful)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("FAILURE", t.toString())
                    ordersService.deleteOrder(order)
                    pendingOperationsService.addPendingOperation(
                        PendingOperations(
                            pendingOperationsService.getNextID(),
                            orderID,
                            "delete"
                        )
                    )
                    callback("connectionError")
                }
            })
        }else {
            callback("nullEntry")
        }
    }
}