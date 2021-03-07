package com.domchow.my_trips_mobile.retrofit

import com.domchow.my_trips_mobile.model.Trip
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("trips")
    fun getTrips() : Call<List<Trip>>

    @POST("trips")
    fun postTrip(@Body trip: Trip) : Call<Trip>

    @PUT("trips/{id}")
    fun putTrip(@Path("id") id: Int, @Body trip: Trip) : Call<Trip>

    @DELETE("trips/{id}")
    fun deleteTrip(@Path("id") id: Int) : Call<Trip>
}
