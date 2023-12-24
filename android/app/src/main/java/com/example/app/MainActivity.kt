package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.app.api.WebSocketListener
import com.example.app.api.WebSocketManager
import com.example.app.model.InstrumentBrands
import com.example.app.model.InstrumentCategories
import com.example.app.model.MusicalInstruments
import com.example.app.model.Orders
import com.example.app.model.Users
import com.example.app.model.db.AppDatabase
import com.example.app.model.response.InstrumentBrandsResponse
import com.example.app.model.response.InstrumentCategoriesResponse
import com.example.app.model.response.MusicalInstrumentsResponse
import com.example.app.model.response.OrdersResponse
import com.example.app.model.response.UsersResponse
import com.example.app.navigation.Navigation
import com.example.app.repository.InstrumentBrandsRepository
import com.example.app.repository.InstrumentCategoriesRepository
import com.example.app.repository.MusicalInstrumentsRepository
import com.example.app.repository.OrdersRepository
import com.example.app.repository.UsersRepository
import com.example.app.service.InstrumentBrandsService
import com.example.app.service.InstrumentCategoriesService
import com.example.app.service.MusicalInstrumentsService
import com.example.app.service.OrdersService
import com.example.app.service.UsersService
import com.example.app.ui.theme.AppTheme
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import okhttp3.WebSocket

class MainActivity : ComponentActivity() {
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            populateDBFromServer(appDatabase)
        }
        setContent {
            AppTheme {
                Navigation(appDatabase = appDatabase)
            }
        }
    }

    private suspend fun populateDBFromServer(appDatabase: AppDatabase) = withContext(Dispatchers.IO) {
        populateInstrumentBrands(appDatabase)
        populateInstrumentCategories(appDatabase)
        populateMusicalInstruments(appDatabase)
        populateOrders(appDatabase)
        populateUsers(appDatabase)
    }

    private suspend fun populateInstrumentBrands(appDatabase: AppDatabase) = withContext(Dispatchers.IO){
        val instrumentBrandsDao = appDatabase.instrumentBrandsDao()
        val instrumentBrandsService = InstrumentBrandsService(InstrumentBrandsRepository(instrumentBrandsDao))

        val webSocketListener = WebSocketListener()
        var webSocketManager = WebSocketManager(webSocketListener)
        webSocketManager.connectWebSocket("ws://10.0.2.2:8080/api/ws/fetch/instrument-brands")

        while (webSocketListener.response == null) {
            delay(2000)
        }

        val serverResponse = webSocketListener.response
        serverResponse?.let {
            try {
                val instrumentBrandsList: List<InstrumentBrandsResponse>

                if (it.startsWith("[")) {
                    instrumentBrandsList = Gson().fromJson(it, object : TypeToken<List<InstrumentBrandsResponse>>() {}.type)
                } else {
                    val singleObject = Gson().fromJson(it, InstrumentBrandsResponse::class.java)
                    instrumentBrandsList = listOf(singleObject)
                }

                CoroutineScope(Dispatchers.IO).launch {
                    instrumentBrandsService.insertAll(instrumentBrandsList.map { response ->
                        InstrumentBrands(
                            response.instrumentBrandID,
                            response.instrumentBrandName,
                            response.pngUrl
                        )
                    })
                }
                Log.d("Test", "Server Response: $serverResponse")
            } catch (e: JsonSyntaxException) {
                Log.e("Test", "Error parsing JSON", e)
            }
        }
    }

    private suspend fun populateInstrumentCategories(appDatabase: AppDatabase) = withContext(Dispatchers.IO){
        val instrumentCategoriesDao = appDatabase.instrumentCategoriesDao()
        val instrumentCategoriesService = InstrumentCategoriesService(InstrumentCategoriesRepository(instrumentCategoriesDao))

        val webSocketListener = WebSocketListener()
        var webSocketManager = WebSocketManager(webSocketListener)
        webSocketManager.connectWebSocket("ws://10.0.2.2:8080/api/ws/fetch/instrument-categories")

        while (webSocketListener.response == null) {
            delay(2000)
        }

        val serverResponse = webSocketListener.response
        serverResponse?.let {
            try {
                val instrumentCategoriesList: List<InstrumentCategoriesResponse>

                if (it.startsWith("[")) {
                    instrumentCategoriesList = Gson().fromJson(it, object : TypeToken<List<InstrumentCategoriesResponse>>() {}.type)
                } else {
                    val singleObject = Gson().fromJson(it, InstrumentCategoriesResponse::class.java)
                    instrumentCategoriesList = listOf(singleObject)
                }

                CoroutineScope(Dispatchers.IO).launch {
                    instrumentCategoriesService.insertAll(instrumentCategoriesList.map { response ->
                        InstrumentCategories(
                            response.instrumentCategoryID,
                            response.instrumentCategoryName,
                            response.pngUrl
                        )
                    })
                }
                Log.d("Test", "Server Response: $serverResponse")
            } catch (e: JsonSyntaxException) {
                Log.e("Test", "Error parsing JSON", e)
            }
        }
    }

    private suspend fun populateMusicalInstruments(appDatabase: AppDatabase) = withContext(Dispatchers.IO){
        val musicalInstrumentsDao = appDatabase.musicalInstrumentsDao()
        val musicalInstrumentsService = MusicalInstrumentsService(MusicalInstrumentsRepository(musicalInstrumentsDao))

        val webSocketListener = WebSocketListener()
        var webSocketManager = WebSocketManager(webSocketListener)
        webSocketManager.connectWebSocket("ws://10.0.2.2:8080/api/ws/fetch/musical-instruments")

        while (webSocketListener.response == null) {
            delay(2000)
        }

        val serverResponse = webSocketListener.response
        serverResponse?.let {
            try {
                val musicalInstrumentsList: List<MusicalInstrumentsResponse>

                if (it.startsWith("[")) {
                    musicalInstrumentsList = Gson().fromJson(it, object : TypeToken<List<MusicalInstrumentsResponse>>() {}.type)
                } else {
                    val singleObject = Gson().fromJson(it, MusicalInstrumentsResponse::class.java)
                    musicalInstrumentsList = listOf(singleObject)
                }

                CoroutineScope(Dispatchers.IO).launch {
                    musicalInstrumentsService.insertAll(musicalInstrumentsList.map { response ->
                        MusicalInstruments(
                            musicalInstrumentID = response.musicalInstrumentID,
                            instrumentCategoryID = response.instrumentCategoryID,
                            instrumentBrandID = response.instrumentBrandID,
                            musicalInstrumentName = response.musicalInstrumentName,
                            description = response.description,
                            pngUrl = response.pngUrl,
                            price = response.price,
                            quantity = response.quantity,
                            onSale = response.onSale
                        )
                    })
                }
                Log.d("Test", "Server Response: $serverResponse")
            } catch (e: JsonSyntaxException) {
                Log.e("Test", "Error parsing JSON", e)
            }
        }
    }

    private suspend fun populateOrders(appDatabase: AppDatabase) = withContext(Dispatchers.IO){
        val ordersDao = appDatabase.ordersDao()
        val ordersService = OrdersService(OrdersRepository(ordersDao))

        val webSocketListener = WebSocketListener()
        var webSocketManager = WebSocketManager(webSocketListener)
        webSocketManager.connectWebSocket("ws://10.0.2.2:8080/api/ws/fetch/orders")

        while (webSocketListener.response == null) {
            delay(2000)
        }

        val serverResponse = webSocketListener.response
        serverResponse?.let {
            try {
                val ordersList: List<OrdersResponse>

                if (it.startsWith("[")) {
                    ordersList = Gson().fromJson(it, object : TypeToken<List<OrdersResponse>>() {}.type)
                } else {
                    val singleObject = Gson().fromJson(it, OrdersResponse::class.java)
                    ordersList = listOf(singleObject)
                }

                CoroutineScope(Dispatchers.IO).launch {
                    ordersService.insertAll(ordersList.map { response ->
                        Orders(
                            orderID = response.orderID,
                            musicalInstrumentID = response.musicalInstrumentID,
                            userID = response.userID,
                            orderedAt = response.orderedAt,
                            quantity = response.quantity,
                            totalPrice = response.totalPrice,
                            deleted = response.deleted
                        )
                    })
                }
                Log.d("Test", "Server Response: $serverResponse")
            } catch (e: JsonSyntaxException) {
                Log.e("Test", "Error parsing JSON", e)
            }
        }
    }

    private suspend fun populateUsers(appDatabase: AppDatabase) = withContext(Dispatchers.IO){
        val usersDao = appDatabase.usersDao()
        val usersService = UsersService(UsersRepository(usersDao))

        val webSocketListener = WebSocketListener()
        var webSocketManager = WebSocketManager(webSocketListener)
        webSocketManager.connectWebSocket("ws://10.0.2.2:8080/api/ws/fetch/users")

        while (webSocketListener.response == null) {
            delay(2000)
        }

        val serverResponse = webSocketListener.response
        serverResponse?.let {
            try {
                val usersList: List<UsersResponse>

                if (it.startsWith("[")) {
                    usersList = Gson().fromJson(it, object : TypeToken<List<UsersResponse>>() {}.type)
                } else {
                    val singleObject = Gson().fromJson(it, UsersResponse::class.java)
                    usersList = listOf(singleObject)
                }

                CoroutineScope(Dispatchers.IO).launch {
                    usersService.insertAll(usersList.map { response ->
                        Users(
                            userID = response.userID,
                            username = response.username,
                            userPassword = response.userPassword
                        )
                    })
                }
                Log.d("Test", "Server Response: $serverResponse")
            } catch (e: JsonSyntaxException) {
                Log.e("Test", "Error parsing JSON", e)
            }
        }
    }
}