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
import utils.CardElement

@Composable
fun HomeScreen() {
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
                    .padding(vertical = 40.dp)

            ){
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
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
                        item {
                            CardElement(cardImage = "https://thenounproject.com/api/private/icons/77928/edit/?backgroundShape=SQUARE&backgroundShapeColor=%23000000&backgroundShapeOpacity=0&exportSize=752&flipX=false&flipY=false&foregroundColor=%23000000&foregroundOpacity=1&imageFormat=png&rotation=0", cardText = "Electric guitars")
                        }

                        item {
                            CardElement(cardImage = "https://thenounproject.com/api/private/icons/367516/edit/?backgroundShape=SQUARE&backgroundShapeColor=%23000000&backgroundShapeOpacity=0&exportSize=752&flipX=false&flipY=false&foregroundColor=%23000000&foregroundOpacity=1&imageFormat=png&rotation=0", cardText = "Bass guitars")
                        }

                        item {
                            CardElement(cardImage = "https://cdn-icons-png.flaticon.com/512/1002/1002081.png", cardText = "Keyboards")
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
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
                        item {
                            CardElement(cardImage = "https://assets.stickpng.com/images/585aad184f6ae202fedf2913.png", cardText = "")
                        }

                        item {
                            CardElement(cardImage = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Gibson_Guitar_logo.svg/1280px-Gibson_Guitar_logo.svg.png", cardText = "")
                        }

                        item {
                            CardElement(cardImage = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Ibanez_logo.svg/2560px-Ibanez_logo.svg.png", cardText = "")
                        }
                    }
                }
            }
        }
    }
}