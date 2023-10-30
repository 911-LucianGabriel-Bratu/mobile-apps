package com.example.app.views

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app.model.MusicalInstruments
import com.example.app.service.MusicalInstrumentsService
import utils.ProductsCardElement

@Composable
fun ProductsScreen(musicalInstrumentsService: MusicalInstrumentsService, onSaleFlag: Boolean,
                   categoryFlag: Boolean, brandFlag: Boolean, category: Int, brand: Int, navController: NavController) {

    val musicalInstrumentsList by remember {
        when {
            onSaleFlag -> musicalInstrumentsService.getMusicalInstrumentsBySale()
            categoryFlag -> musicalInstrumentsService.getMusicalInstrumentsByInstrumentCategory(category)
            brandFlag -> musicalInstrumentsService.getMusicalInstrumentsByBrand(brand)
            else -> musicalInstrumentsService.allMusicalInstruments
        }
    }.collectAsState(initial = emptyList())

    LazyColumn {
        items(musicalInstrumentsList) { musicalInstrument ->
            Row(
                modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProductsCardElement(
                    instrumentID = musicalInstrument.musicalInstrumentID,
                    instrumentName = musicalInstrument.musicalInstrumentName,
                    description = musicalInstrument.description,
                    cardImage = musicalInstrument.pngUrl,
                    quantity = musicalInstrument.quantity,
                    price = musicalInstrument.price,
                    navController = navController
                )
            }
        }
    }
}