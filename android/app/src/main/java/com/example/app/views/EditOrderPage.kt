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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.app.api.RetrofitClient
import com.example.app.model.Orders
import com.example.app.model.PendingOperations
import com.example.app.service.OrdersService
import com.example.app.service.PendingOperationsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date
import kotlin.coroutines.coroutineContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOrderPage(ordersService: OrdersService, navController: NavController, pendingOperationsService: PendingOperationsService){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val orderID = navBackStackEntry?.arguments?.getString("data")
    val quantity = navBackStackEntry?.arguments?.getString("quantity")
    var mutableQuantity by remember { mutableStateOf(quantity)}
    val totalPrice = navBackStackEntry?.arguments?.getString("price")
    val currentContext = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Modify order no. $orderID:",
            color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center,
            fontSize = 32.sp, overflow = TextOverflow.Ellipsis, lineHeight = 50.sp)
        Spacer(Modifier.height(200.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            if (quantity != null) {
                var quantityState by remember { mutableStateOf(quantity)}
                TextField(
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    value = quantityState,
                    onValueChange = {
                        quantityState = it
                        mutableQuantity = it
                    },
                    label = { Text(text = "Quantity: ", color = MaterialTheme.colorScheme.primary) }
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    if(mutableQuantity != null && totalPrice != null){
                        if(mutableQuantity!!.toInt() > 0){
                            updateOrder(Integer.parseInt(orderID), mutableQuantity!!.toInt(), Integer.parseInt(quantity), totalPrice.toFloat(), ordersService, pendingOperationsService){
                                wasSuccessful ->
                                if (wasSuccessful == "successful") {
                                    Toast.makeText(currentContext, "Order updated successfully", Toast.LENGTH_SHORT).show()
                                    navController.navigateUp()
                                } else if(wasSuccessful == "serverError"){
                                    Toast.makeText(currentContext, "Server has encountered an error. Could not update order.", Toast.LENGTH_SHORT).show()
                                }
                                else if(wasSuccessful == "connectionError"){
                                    Toast.makeText(currentContext, "You or the server may be offline. Order was updated locally", Toast.LENGTH_SHORT).show()
                                    navController.navigateUp()
                                }
                                else if(wasSuccessful == "nullEntry"){
                                    Toast.makeText(currentContext, "Null entry. Should not have happened.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        else{
                            Toast.makeText(currentContext, "Quantity must be greater than 0!", Toast.LENGTH_SHORT).show()
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

fun updateOrder(
    orderID: Int,
    quantity: Int,
    oldQuantity: Int,
    totalPrice: Float,
    ordersService: OrdersService,
    pendingOperationsService: PendingOperationsService,
    callback: (String) -> Unit
){
    CoroutineScope(Dispatchers.Main).launch {
        var wasSuccessful: String
        val total: Float = (totalPrice/oldQuantity) * quantity
        val oldOrder: Orders? = ordersService.getOrderByID(orderID)
        if(oldOrder != null){
            val newOrder: Orders = Orders(orderID, oldOrder.musicalInstrumentID, oldOrder.userID, Date(), quantity, total, false)
            val orderApi = RetrofitClient.getOrderApi()

            val call = orderApi.updateOrder(orderID, newOrder)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        ordersService.updateOrder(newOrder)
                        wasSuccessful = "successful"
                    } else {
                        wasSuccessful = "serverError"
                    }
                    callback(wasSuccessful)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("FAILURE", t.toString())
                    ordersService.updateOrder(newOrder)
                    pendingOperationsService.addPendingOperation(
                        PendingOperations(
                            pendingOperationsService.getNextID(),
                            newOrder.orderID,
                            "update"
                        )
                    )
                    callback("connectionError")
                }
            })
        }
        else {
            callback("nullEntry")
        }
    }
}



