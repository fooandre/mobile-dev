package com.example.assignment.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.assignment.R

class MovieAdapter(private val context: Context, private val data: MutableList<Movie>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount() = data.size

    override fun getItem(index: Int) = data[index]

    override fun getItemId(index: Int) = 0.toLong()

    override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
        val movie = data[index]
        val view = inflater.inflate(R.layout.movie, parent, false)

        view.findViewById<TextView>(R.id.nameView).text = movie.title
        view.findViewById<TextView>(R.id.dateView).text = movie.releaseDate
        val lang = movie.language.toString()
        view.findViewById<TextView>(R.id.languageView).text = "${lang[0]}${lang.substring(1).lowercase()}"
        if (movie.violence || movie.languageUsed) view.findViewById<ImageView>(R.id.warningView).visibility = View.VISIBLE

        return view
    }
}