package com.example.app.views

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.app.model.db.AppDatabase
import com.example.app.repository.InstrumentBrandsRepository
import com.example.app.repository.InstrumentCategoriesRepository
import com.example.app.service.InstrumentBrandsService
import com.example.app.service.InstrumentCategoriesService
import utils.CardElement

@Composable
fun HomeScreen(instrumentBrandsService: InstrumentBrandsService, instrumentCategoriesService: InstrumentCategoriesService) {
    val categoriesList by instrumentCategoriesService.allInstrumentCategories.collectAsState(initial = emptyList())

    val brandsList by instrumentBrandsService.allInstrumentBrands.collectAsState(initial = emptyList())
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Transparent,
    ){

        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)

            ){
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = "https://uk.yamaha.com/en/files/01_4daddcd68b7c7ed989db04be709a653a.jpg?impolicy=resize&imwid=1200&imhei=480",
                    contentDescription = "",
                    alignment = Alignment.TopCenter
                )
            }

            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.radialGradient(
                            listOf(
                                Color(0xFF833F00),
                                Color(0xFF753700), Color(0xFF693300)
                            )
                        )
                    )
                    .padding(vertical = 40.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Available categories", style = TextStyle(color = MaterialTheme.colorScheme.background, fontSize = 24.sp, textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline))
                    LazyRow(
                        flingBehavior = ScrollableDefaults.flingBehavior(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        categoriesList.forEach {category ->
                            item {
                                CardElement(cardImage = category.pngUrl, cardText = category.instrumentCategoryName)
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Popular Brands", style = TextStyle(color = MaterialTheme.colorScheme.background, fontSize = 24.sp, textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline))
                    LazyRow(
                        flingBehavior = ScrollableDefaults.flingBehavior(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        brandsList.forEach{brand ->
                            item{
                                CardElement(cardImage = brand.pngUrl, cardText = "")
                            }
                        }
                    }
                }
            }
        }
    }
}