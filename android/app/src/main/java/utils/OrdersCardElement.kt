package utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app.navigation.Routes
import java.util.Date

@Composable
fun OrdersCardElement(orderID: Int, orderedAt: Date, quantity: Int, totalPrice: Float, navController: NavController){

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

            Text(text = "Order no. $orderID", fontSize = 24.sp, textDecoration = TextDecoration.Underline, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(10.dp))
            Text(text = "Ordered at: $orderedAt", color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text(text = "Quantity: $quantity", color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Text(text = "Price: $totalPrice", color = MaterialTheme.colorScheme.tertiaryContainer, textAlign = TextAlign.Center)
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { navController.navigate(Routes.editOrder.replace("{data}", orderID.toString(), ignoreCase = true)) }
                )
                {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(Modifier.height(10.dp))
                IconButton(
                    onClick = { navController.navigate(Routes.cancelOrder.replace("{data}", orderID.toString(), ignoreCase = true)) }
                )
                {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }

}