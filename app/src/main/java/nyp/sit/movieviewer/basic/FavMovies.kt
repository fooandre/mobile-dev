package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import nyp.sit.movieviewer.basic.data.DatabaseAdapter
import nyp.sit.movieviewer.basic.data.ListViewAdapter
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem

class FavMovies : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_fav_movies)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val favoriteMovies = getMovies()
		val movies = ArrayList<SimpleMovieItem>()
		for (movie in favoriteMovies) movies.add(SimpleMovieSampleData.simpleMovieitemArray.first { it.title == movie.title })

		val listView = findViewById<ListView>(R.id.movielist)
		listView.adapter = ListViewAdapter(this, movies)
		listView.setOnItemClickListener { _, _, position, _ ->
			val intent = Intent(this, SimpleItemDetailActivity::class.java)
			intent.putExtra("position", position)
			startActivity(intent)
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		val intent =Intent(this, SimpleViewListOfMoviesActivity::class.java)
		startActivity(intent)
		return super.onSupportNavigateUp()
	}

	private fun getMovies(): ArrayList<SimpleMovieItem> {
		var movies = ArrayList<SimpleMovieItem>()
		val db = DatabaseAdapter(applicationContext)
		val cursor = db.getMovies()

		if (cursor == null || cursor.count == 0) return ArrayList()
		cursor.moveToFirst()
		do {
			val title = cursor.getString(1)
			val overview = cursor.getString(2)
			val releaseDate = cursor.getString(3)
			val language = cursor.getString(4)
			movies.add(SimpleMovieItem(overview, releaseDate, language, title))
		} while (cursor.moveToNext())

		return movies
	}
}