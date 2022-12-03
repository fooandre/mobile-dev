package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.Language
import com.example.assignment.models.Movie
import com.example.assignment.models.MovieAdapter
import kotlin.math.log

class MainActivity : AppCompatActivity() {
	private val movies = mutableListOf<Movie>(
		Movie("peepee", "poopoo", "23-12-2022", true),
		Movie("quack", "guagua", "31-10-2002")
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		var listView = findViewById<ListView>(R.id.moviesLV)
		listView.adapter = MovieAdapter(this, movies)
		listView.setOnItemClickListener { adapter, _, index, _ ->
			var movie = adapter.getItemAtPosition(index) as Movie
			val intent = Intent(this, MovieDetail::class.java)
			intent.putExtra("title", movie.title)
			intent.putExtra("description", movie.description)
			intent.putExtra("language", if (movie.language == Language.ENGLISH) "English" else if (movie.language == Language.CHINESE) "Chinese" else if (movie.language == Language.MALAY) "Malay" else "Tamil")
			intent.putExtra("date", movie.releaseDate)
			intent.putExtra("violence", movie.violence)
			intent.putExtra("languageUsed", movie.languageUsed)
			startActivity(intent)
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.main_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.goToAdd) {
			var intent = Intent(this, AddMovie::class.java)
			startActivity(intent)
		}
		return super.onOptionsItemSelected(item)
	}
}