package com.example.assignment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.Movie

class MovieDetail : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_movie_detail)
		var movie = Movie("Venom",
			"When Eddie Brooks acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life.",
			"03-10-2018")
		findViewById<TextView>(R.id.titleTV).text = movie.title
		findViewById<TextView>(R.id.descTV).text = movie.description
		findViewById<TextView>(R.id.languageTV).text = "${movie.language.toString().substring(0, 1)} ${
			movie.language.toString().substring(1).lowercase()
		}"
		findViewById<TextView>(R.id.dateTV).text = movie.releaseDate

		var reason = mutableListOf<String>()
		if (movie.violence) reason.add("Violence")
		if (movie.languageUsed) reason.add("Language")
		findViewById<TextView>(R.id.suitableTV).text =
			if (movie.violence || movie.languageUsed) "No (${reason.joinToString("&")})" else "Yes"
	}
}