package com.domchow.my_trips_mobile.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.domchow.my_trips_mobile.R
import com.domchow.my_trips_mobile.adapter.TripAdapter
import com.domchow.my_trips_mobile.model.Trip
import com.domchow.my_trips_mobile.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_trip_popup.view.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.lang.Thread.sleep
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TOP_BAR
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_toolbar)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        //DELETE TRIP
        fun delete(id: Int) {
            mainActivityViewModel.deleteTrip(id)
            sleep(500)
            mainActivityViewModel.getTrips()
        }

        fun createTripClickListener (){
            val myDialogView = LayoutInflater.from(this).inflate(R.layout.activity_update_trip_popup, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(myDialogView)
                .setTitle("CREATE TRIP")
                .show()

            mBuilder.create()

            myDialogView.dialogSubmit.setOnClickListener {
                fun DatePicker.getDate(): Date {
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    return calendar.time
                }

                var dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                var strDate = dateFormat.format(myDialogView.dialog_date.getDate())

                val trip = Trip(city = myDialogView.dialog_city.text.toString(), date = strDate)
                mainActivityViewModel.createTrip(trip)
                sleep(500)
                mainActivityViewModel.getTrips()
                mBuilder.dismiss()
            }

            mBuilder.show()
        }

        fun updateTripClickListener (id: Int){
            val myDialogView = LayoutInflater.from(this).inflate(R.layout.activity_update_trip_popup, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(myDialogView)
                .setTitle("UPDATE TRIP")
                .show()

            mBuilder.create()

            myDialogView.dialogSubmit.setOnClickListener {
                fun DatePicker.getDate(): Date {
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    return calendar.time
                }

                var dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                var strDate = dateFormat.format(myDialogView.dialog_date.getDate())

                val trip = Trip(city = myDialogView.dialog_city.text.toString(), date = strDate)
                mainActivityViewModel.updateTrip(trip, id)
                sleep(500)
                mainActivityViewModel.getTrips()
                mBuilder.dismiss()
            }

            mBuilder.show()
        }

        //POPULATE List
        mainActivityViewModel
            .getTrips()!!
            .observe(this, Observer { serviceSetterGetter ->
                val adapter = TripAdapter(this, serviceSetterGetter, ::delete, ::updateTripClickListener)
                trips_list_view.adapter = adapter
            })

        //ADD BUTTON HANDLE
        topBarAdd.setOnClickListener{createTripClickListener()}

        topBarRefresh.setOnClickListener {
            mainActivityViewModel
                .getTrips()
        }
    }
}
