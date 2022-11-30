package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.Movie
import com.example.assignment.models.MovieAdapter

class MainActivity : AppCompatActivity() {
	private val movies = mutableListOf<Movie>(
		Movie("peepee", "poopoo", "23-12-2022"),
		Movie("quack", "guagua", "31-10-2002")
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		findViewById<ListView>(R.id.moviesLV).adapter = MovieAdapter(this, movies)
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