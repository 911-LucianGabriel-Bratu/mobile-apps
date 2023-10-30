package utils

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.app.navigation.Routes
import com.example.app.views.ConfirmationPage

@Composable
fun ProductsCardElement(instrumentID: Int, instrumentName: String, description: String,
                        cardImage: String, quantity: Int, price: Float, navController: NavController){
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

            AsyncImage(model = cardImage, contentDescription = "", modifier = Modifier.size(width = 120.dp, height = 120.dp))
            Spacer(Modifier.height(10.dp))
            Text(text = instrumentName, color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text(text = description, color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text(text = "Quantity: $quantity", color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text(text = "Price: $price", color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Button(onClick = { navController.navigate(Routes.confirmation.replace("{data}", instrumentID.toString(), ignoreCase = true)) }) {
                Text(text = "Purchase")
            }
        }
    }
}