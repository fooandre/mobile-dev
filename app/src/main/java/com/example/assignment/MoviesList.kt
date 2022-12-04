package com.example.assignment

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.DbAdapter
import com.example.assignment.models.Language
import com.example.assignment.models.Movie
import com.example.assignment.models.MovieAdapter

class MoviesList : AppCompatActivity() {
	private val movies = mutableListOf<Movie>(
//		Movie("peepee", "poopoo", "23-12-2022", true),
//		Movie("quack", "guagua", "31-10-2002")
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_movies_list)

		getMovies(applicationContext)

		val listView = findViewById<ListView>(R.id.moviesLV)
		listView.adapter = MovieAdapter(this, movies)
		listView.setOnItemClickListener { adapter, _, index, _ ->
			val movie = adapter.getItemAtPosition(index) as Movie
			val intent = Intent(this, MovieDetail::class.java)
			intent.putExtra("id", movie._id)
			intent.putExtra("title", movie.title)
			intent.putExtra("description", movie.description)
			intent.putExtra("language", if (movie.language == Language.ENGLISH) "English" else if (movie.language == Language.CHINESE) "Chinese" else if (movie.language == Language.MALAY) "Malay" else "Tamil")
			intent.putExtra("date", movie.releaseDate)
			intent.putExtra("violence", movie.violence)
			intent.putExtra("languageUsed", movie.languageUsed)
			startActivity(intent)
		}

		listView.setOnItemLongClickListener { adapter, parent, index, _ ->
			val popup = PopupMenu(this, parent)
			popup.menuInflater.inflate(R.menu.main_popup_menu, popup.menu)
			popup.show()

			popup.setOnMenuItemClickListener {
				val movie = adapter.getItemAtPosition(index) as Movie
				val intent = Intent(this, EditMovie::class.java)
				intent.putExtra("id", movie._id)
				intent.putExtra("title", movie.title)
				intent.putExtra("description", movie.description)
				intent.putExtra("language", if (movie.language == Language.ENGLISH) "English" else if (movie.language == Language.CHINESE) "Chinese" else if (movie.language == Language.MALAY) "Malay" else "Tamil")
				intent.putExtra("date", movie.releaseDate)
				intent.putExtra("violence", movie.violence)
				intent.putExtra("languageUsed", movie.languageUsed)
				startActivity(intent)
				true
			}
			true
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.goToAdd) {
			val intent = Intent(this, AddMovie::class.java)
			startActivity(intent)
		}
		return super.onOptionsItemSelected(item)
	}

	fun getMovies(context: Context): List<Movie> {
		movies.clear()

		val cursor: Cursor?
		val db = DbAdapter(context)
		db.open()

		cursor = db.getAll()
		if (cursor != null && cursor.count > 0) {
			cursor.moveToFirst()
			do {
				movies.add(Movie(
					cursor.getLong(0),
					cursor.getString(1),
					cursor.getString(2),
					cursor.getString(3),
					cursor.getInt(4) == 1,
					cursor.getInt(5) == 1,
					if (cursor.getString(6) == "English") Language.ENGLISH else if (cursor.getString(6) == "Chinese") Language.CHINESE else if (cursor.getString(6) == "Malay") Language.MALAY else Language.TAMIL
				))
			}
			while (cursor.moveToNext())
		}
		return movies
	}
}