package com.example.assignment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.Movie

class RatingActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_rating)
		var movie = Movie(
			"Venom",
			"When Eddie Brooks acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life.",
			"English",
			"03-10-2018",
			true
		)
		findViewById<TextView>(R.id.titleTV).text = "Enter your review for the movie: ${movie.title}"
	}
}