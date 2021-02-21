package com.domchow.my_trips_mobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.domchow.my_trips_mobile.R
import com.domchow.my_trips_mobile.model.Trip
import com.squareup.picasso.Picasso


class TripAdapter(
    private val context: Context,
    private val dataSource: List<Trip>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_trip, parent, false)

        val titleTextView = rowView.findViewById(R.id.recipe_list_title) as TextView
        val subtitleTextView = rowView.findViewById(R.id.recipe_list_subtitle) as TextView
        val detailTextView = rowView.findViewById(R.id.recipe_list_detail) as TextView
        val thumbnailImageView = rowView.findViewById(R.id.recipe_list_thumbnail) as ImageView

        val trip = getItem(position) as Trip

        titleTextView.text = trip.city
        subtitleTextView.text = trip.date
        detailTextView.text = "LABEL"

        Picasso.with(context).load(R.mipmap.ic_launcher).into(thumbnailImageView)

        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}