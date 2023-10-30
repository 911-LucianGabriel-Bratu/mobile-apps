package com.example.app.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.app.model.MusicalInstruments
import com.example.app.model.Orders
import com.example.app.service.MusicalInstrumentsService
import com.example.app.service.OrdersService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@Composable
fun ConfirmationPage(navController: NavController,
                     musicalInstrumentsService: MusicalInstrumentsService, ordersService: OrdersService){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val instrumentID = navBackStackEntry?.arguments?.getString("data")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$instrumentID")
        Spacer(modifier = Modifier.padding(PaddingValues(5.dp)))
        Row(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)) {
            Button(onClick = {
                navController.navigateUp()
                placeOrder(Integer.parseInt(instrumentID), musicalInstrumentsService, ordersService)
            }){
                Text(text = "Confirm")
            }
            Spacer(modifier = Modifier.padding(PaddingValues(horizontal = 5.dp)))
            Button(onClick = {
                navController.navigateUp()
            }){
                Text(text = "Cancel")
            }
        }
    }
}

fun placeOrder(instrumentID: Int, musicalInstrumentsService: MusicalInstrumentsService,
               ordersService: OrdersService){
    CoroutineScope(Dispatchers.IO).launch {
        val musicalInstrument: MusicalInstruments? = musicalInstrumentsService.getMusicalInstrumentByID(instrumentID)
        if (musicalInstrument != null) {
            ordersService.addOrder(Orders(0, musicalInstrument.musicalInstrumentID, 1, Date(), 1, musicalInstrument.price, false))
        }
    }

}