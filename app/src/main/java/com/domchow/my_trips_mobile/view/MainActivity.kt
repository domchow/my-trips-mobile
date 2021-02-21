package com.domchow.my_trips_mobile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.domchow.my_trips_mobile.R
import com.domchow.my_trips_mobile.adapter.TripAdapter
import com.domchow.my_trips_mobile.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel
            .getTrips()!!
            .observe(this, Observer { serviceSetterGetter ->
                val adapter = TripAdapter(this, serviceSetterGetter)
                trips_list_view.adapter = adapter

            })
    }
}
