package com.example.assignment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.Movie

class MovieDetail : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_movie_detail)
		var movie = Movie(
			"Venom",
			"When Eddie Brooks acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life.",
			"English",
			"03-10-2018",
			true
		)
		findViewById<TextView>(R.id.titleTV).text = movie.title
		findViewById<TextView>(R.id.descTV).text = movie.description
		findViewById<TextView>(R.id.languageTV).text = movie.language
		findViewById<TextView>(R.id.dateTV).text = movie.releaseDate
		findViewById<TextView>(R.id.suitableTV).text = if (movie.suitableForChildren) "Yes" else "No"
	}
}