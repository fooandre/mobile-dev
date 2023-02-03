package nyp.sit.movieviewer.advanced

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import nyp.sit.movieviewer.advanced.entity.MovieItem

class ListViewAdapter(private val context: Context, private val data: MutableList<MovieItem>) : BaseAdapter() {
	private val inflater = LayoutInflater.from(context)

	override fun getCount(): Int = data.size
	override fun getItem(index: Int): MovieItem = data[index]
	override fun getItemId(p0: Int): Long = 0.toLong()

	override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
		val movie = getItem(index)
		val view = inflater.inflate(R.layout.movie_item, parent, false)
		view.movieName.text = movie.title

		val imageUrl = NetworkUtils.buildImageUrl(movie.poster_path)
		Picasso.get().load(imageUrl?.toString()).into(view.movieImage)
		return view
	}
}