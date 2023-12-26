package com.example.app.api

import com.example.app.model.Orders
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrdersAPI {
    @POST("/orders")
    fun createOrder(@Body order: Orders): Call<Void>

    @PUT("/orders/{id}")
    fun updateOrder(@Path("id") orderId: Int, @Body order: Orders): Call<Void>

    @DELETE("/orders/{id}")
    fun deleteOrder(@Path("id") orderId: Int): Call<Void>
}