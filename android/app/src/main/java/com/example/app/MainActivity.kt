package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.app.appbar.AppBar
import com.example.app.drawer.DrawerBody
import com.example.app.drawer.DrawerHeader
import com.example.app.drawer.MenuItem
import com.example.app.ui.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainContent()
                HideSystemUi()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = MaterialTheme.colorScheme.secondary){
                DrawerHeader()
                DrawerBody(items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Main menu",
                        contentDescription = "Main menu",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "orders",
                        title = "My orders",
                        contentDescription = "My orders",
                        icon = Icons.Default.ShoppingCart
                    ),
                    MenuItem(
                        id = "products",
                        title = "All products",
                        contentDescription = "All products",
                        icon = Icons.Default.List
                    ),
                    MenuItem(
                        id = "products_sale",
                        title = "Products on sale",
                        contentDescription = "Products on sale",
                        icon = Icons.Default.Favorite
                    ),
                    MenuItem(
                        id = "deliveries",
                        title = "About deliveries",
                        contentDescription = "About deliveries",
                        icon = Icons.Default.LocationOn
                    ),
                    MenuItem(
                        id = "about",
                        title = "About us",
                        contentDescription = "About us",
                        icon = Icons.Default.Info
                    )
                ),
                    onItemClick = {
                        println("Clicked on ${it.title}")
                    })
            }
        },
        gesturesEnabled = drawerState.isOpen
    ) {
        Scaffold(
            topBar = {
                AppBar(onNavigationIconClick = {
                    scope.launch{
                        drawerState.apply {
                            if(isClosed) open() else close()
                        }
                    }
                })
            },
        ) {
            innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                Text(text = "Something")
            }
        }
    }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T{
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

@Composable
fun HideSystemUi(){
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.isSystemBarsVisible = false
        systemUiController.isStatusBarVisible = false
        systemUiController.isNavigationBarVisible = false
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        HideSystemUi()
    }
}