package com.example.app.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.concurrent.TimeUnit

class WebSocketManager(private val listener: WebSocketListener) {

    val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(3, TimeUnit.SECONDS)
        .writeTimeout(3, TimeUnit.SECONDS)
        .build()
    var webSocket: WebSocket? = null

    fun connectWebSocket(serverUrl: String) {
        Log.d("test","Connecting to WebSocket: $serverUrl")
        val request = Request.Builder()
            .url(serverUrl)
            .build()

        webSocket = client.newWebSocket(request, listener)
    }

    fun sendMessage(content: String) {
        webSocket?.send(content)
    }

    fun disconnectWebSocket() {
        webSocket?.close(1000, "Goodbye!")
    }
}