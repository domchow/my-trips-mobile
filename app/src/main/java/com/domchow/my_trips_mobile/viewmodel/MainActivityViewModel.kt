package com.domchow.my_trips_mobile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domchow.my_trips_mobile.model.Trip
import com.domchow.my_trips_mobile.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<List<Trip>>? = null

    fun getTrips() : LiveData<List<Trip>>? {
        return MainActivityRepository.getAllTripsApiCall()
    }

    fun createTrip(trip: Trip) {
        MainActivityRepository.postTrip(trip)
    }

    fun updateTrip(trip: Trip, id: Int) {
        MainActivityRepository.putTrip(trip, id)
    }

    fun deleteTrip(id: Int) {
        MainActivityRepository.deleteTrip(id)
    }
}
