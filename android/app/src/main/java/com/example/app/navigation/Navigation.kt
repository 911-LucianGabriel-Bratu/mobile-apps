package com.example.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.appbar.AppBar
import com.example.app.drawer.DrawerBody
import com.example.app.drawer.DrawerHeader
import com.example.app.drawer.MenuItem
import com.example.app.model.db.AppDatabase
import com.example.app.views.AboutScreen
import com.example.app.views.DeliveriesScreen
import com.example.app.views.HomeScreen
import com.example.app.views.LoginForm
import com.example.app.views.OrdersScreen
import com.example.app.views.ProductsScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun Navigation(appDatabase: AppDatabase) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.login){
        composable(
            route = Routes.login,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ){
            LoginScreen(navController)
        }
        composable(
            route = Routes.home,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }
        ){
            MainContent("Home", navController) { HomeScreen(appDatabase) }
        }
        composable(
            route = Routes.orders,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ){
            MainContent("Orders", navController) { OrdersScreen() }
        }
        composable(
            route = Routes.products,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ){
            MainContent("Products", navController) { ProductsScreen() }
        }
        composable(
            route = Routes.products_sale,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ){
            MainContent("Products", navController) { ProductsScreen() }
        }
        composable(
            route = Routes.deliveries,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ){
            MainContent("Deliveries", navController) { DeliveriesScreen() }
        }
        composable(
            route = Routes.about,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ){
            MainContent("About", navController) { AboutScreen() }
            AboutScreen()
        }
        composable(
            route = Routes.logout,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ){
            LoginScreen(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    appBarTitle: String,
    navController: NavController,
    auxContainer : @Composable() () -> Unit
){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = MaterialTheme.colorScheme.secondary, drawerContentColor = MaterialTheme.colorScheme.background){
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
                    ),
                    MenuItem(
                        id = "logout",
                        title = "Logout",
                        contentDescription = "Logout",
                        icon = Icons.Default.ExitToApp
                    )
                ),
                    onItemClick = { item ->
                        when(item.id){
                            "home" -> {
                                navController.navigate(route = item.id)
                            }
                            "orders" -> {
                                navController.navigate(route = item.id)
                            }
                            "products" -> {
                                navController.navigate(route = item.id)
                            }
                            "products_sale" -> {
                                navController.navigate(route = item.id)
                            }
                            "deliveries" -> {
                                navController.navigate(route = item.id)
                            }
                            "about" -> {
                                navController.navigate(route = item.id)
                            }
                            "logout" -> {
                                navController.navigate(route = item.id)
                            }
                        }
                    })
            }
        },
        gesturesEnabled = drawerState.isOpen
    ) {
        Scaffold(
            topBar = {
                AppBar(appBarTitle ,onNavigationIconClick = {
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
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ){
                auxContainer()
            }
        }

    }
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

@Composable
fun LoginScreen(navController: NavController) {
    LoginForm(navController)
}