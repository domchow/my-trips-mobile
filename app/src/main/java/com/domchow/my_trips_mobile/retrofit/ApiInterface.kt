package com.domchow.my_trips_mobile.retrofit

import com.domchow.my_trips_mobile.model.Trip
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("trips")
    fun getServices() : Call<List<Trip>>
}