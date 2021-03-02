package com.domchow.my_trips_mobile.retrofit

import com.domchow.my_trips_mobile.model.Trip
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("trips")
    fun getTrips() : Call<List<Trip>>

    @POST("trips")
    fun postTrip(@Body trip: Trip) : Call<Trip>
}