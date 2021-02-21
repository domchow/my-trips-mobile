package com.domchow.my_trips_mobile.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.domchow.my_trips_mobile.retrofit.RetrofitClient
import com.domchow.my_trips_mobile.model.Trip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<List<Trip>>()

    fun getServicesApiCall(): MutableLiveData<List<Trip>> {

        val call = RetrofitClient.apiInterface.getServices()

        call.enqueue(object: Callback<List<Trip>> {
            override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<List<Trip>>,
                response: Response<List<Trip>>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                serviceSetterGetter.value = response.body()
            }
        })

        return serviceSetterGetter
    }
}