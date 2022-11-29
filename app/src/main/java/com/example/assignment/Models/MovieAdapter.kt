package com.example.assignment.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class MovieAdapter(private val context: Context, private val data: ArrayList<Movie>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount() = data.size

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(p0: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}