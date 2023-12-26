package com.example.app.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import coil.compose.AsyncImage
import com.example.app.api.RetrofitClient
import com.example.app.model.MusicalInstruments
import com.example.app.model.Orders
import com.example.app.model.PendingOperations
import com.example.app.navigation.Routes
import com.example.app.service.MusicalInstrumentsService
import com.example.app.service.OrdersService
import com.example.app.service.PendingOperationsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import java.util.Date
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ConfirmationPage(navController: NavController,
                     musicalInstrumentsService: MusicalInstrumentsService, ordersService: OrdersService,
                        pendingOperationsService: PendingOperationsService){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val instrumentID = navBackStackEntry?.arguments?.getString("data")
    val musicalInstrument = remember { mutableStateOf<MusicalInstruments?>(null) }
    val currentContext = LocalContext.current

    LaunchedEffect(instrumentID) {
        instrumentID?.let { id ->
            musicalInstrument.value =
                musicalInstrumentsService.getMusicalInstrumentByID(Integer.parseInt(id))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Are you sure you want to purchase this instrument?",
            color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center,
            fontSize = 48.sp, overflow = TextOverflow.Ellipsis, lineHeight = 50.sp)

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier
                .size(width = 320.dp, height = 320.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(model = musicalInstrument.value?.pngUrl, contentDescription = "", modifier = Modifier.size(width = 120.dp, height = 120.dp))
                Spacer(Modifier.height(10.dp))
                musicalInstrument.value?.let { Text(text = it.musicalInstrumentName, color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center) }
                Spacer(Modifier.height(10.dp))
                musicalInstrument.value?.let { Text(text = it.description, color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center) }
                Spacer(Modifier.height(10.dp))
                Text(text = "Price: ${musicalInstrument.value?.price}", color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center)
                Spacer(Modifier.height(10.dp))
            }
        }

        Spacer(modifier = Modifier.padding(PaddingValues(5.dp)))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        )
        {
            Row {
                Button(onClick = {
                    placeOrder(
                        Integer.parseInt(instrumentID),
                        musicalInstrumentsService,
                        ordersService,
                        pendingOperationsService
                    ){
                        wasSuccessful ->
                        if (wasSuccessful == "successful") {
                            Toast.makeText(currentContext, "Order placed successfully", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        } else if(wasSuccessful == "serverError"){
                            Toast.makeText(currentContext, "Server has encountered an error. Could not place order.", Toast.LENGTH_SHORT).show()
                        }
                        else if (wasSuccessful == "connectionError"){
                            Toast.makeText(currentContext, "You or the server may be offline. Order was placed locally", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        }
                        else if (wasSuccessful == "nullEntry"){
                            Toast.makeText(currentContext, "Null entry. Should not have happened.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                ) {
                    Text(text = "Confirm")
                }
                Spacer(modifier = Modifier.padding(PaddingValues(horizontal = 5.dp)))
                Button(onClick = {
                    navController.navigateUp()
                }
                ) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}

fun placeOrder(
    instrumentID: Int,
    musicalInstrumentsService: MusicalInstrumentsService,
    ordersService: OrdersService,
    pendingOperationsService: PendingOperationsService,
    callback: (String) -> Unit
) {
    CoroutineScope(Dispatchers.Main).launch {
        var wasSuccessful: String
        val musicalInstrument: MusicalInstruments? = musicalInstrumentsService.getMusicalInstrumentByID(instrumentID)

        if (musicalInstrument != null) {
            val newOrder = Orders(
                ordersService.getNextID(),
                musicalInstrument.musicalInstrumentID,
                1,
                Date(),
                1,
                musicalInstrument.price,
                false
            )
            val orderApi = RetrofitClient.getOrderApi()

            val call = orderApi.createOrder(newOrder)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        ordersService.addOrder(newOrder)
                        wasSuccessful = "successful"
                    } else {
                        wasSuccessful = "serverError"
                    }
                    callback(wasSuccessful)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("FAILURE", t.toString())
                    ordersService.addOrder(newOrder)
                    pendingOperationsService.addPendingOperation(PendingOperations(
                        pendingOperationsService.getNextID(),
                        newOrder.orderID,
                        "create"
                    ))
                    callback("connectionError")
                }
            })
        } else {
            callback("nullEntry")
        }
    }
}