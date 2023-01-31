package nyp.sit.movieviewer.basic.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import nyp.sit.movieviewer.basic.R
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem

class ListViewAdapter(private val context: Context, private val data: ArrayList<SimpleMovieItem>) : BaseAdapter() {
    private val _inflater = LayoutInflater.from(context)

    override fun getCount() = data.size
    override fun getItem(index: Int) = data[index]
    override fun getItemId(p0: Int) = 0.toLong()

    override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
        val movie = getItem(index)
        val view = _inflater.inflate(R.layout.simpleitem, parent, false)

        view.findViewById<TextView>(R.id.movie_name).text = movie.title!!
        view.findViewById<TextView>(R.id.movie_release_date).text = movie.release_date!!
        view.findViewById<TextView>(R.id.movie_language).text = movie.original_langauge!!

        return view
    }
}