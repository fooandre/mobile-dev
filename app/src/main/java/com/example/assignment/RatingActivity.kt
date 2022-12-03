package com.example.assignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.DbAdapter
import com.example.assignment.models.Language
import com.example.assignment.models.Movie

class RatingActivity : AppCompatActivity() {
	private var movie: Movie? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_rating)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		findViewById<TextView>(R.id.titleTV).text = "Enter your review for the movie ${intent.getStringExtra("title")}"
		movie = Movie(
			intent.getLongExtra("id", -1),
			intent.getStringExtra("title")!!,
			intent.getStringExtra("description")!!,
			intent.getStringExtra("date")!!,
            intent.getBooleanExtra("violence", false),
            intent.getBooleanExtra("languageUsed", false),
			if (intent.getStringExtra("language") == "English") Language.ENGLISH else if (intent.getStringExtra("language") == "Chinese") Language.CHINESE else if (intent.getStringExtra("language") == "Malay") Language.MALAY else Language.TAMIL
		)
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return super.onSupportNavigateUp()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.add_review_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		var intent = Intent(this, MovieDetail::class.java)
		intent.putExtra("id", movie!!._id)
		intent.putExtra("title", movie!!.title)
		intent.putExtra("description", movie!!.description)
		intent.putExtra("date", movie!!.releaseDate)
		intent.putExtra("language", if (movie!!.language == Language.ENGLISH) "English" else if (movie!!.language == Language.CHINESE) "Chinese" else if (movie!!.language == Language.MALAY) "Malay" else "Tamil")
		intent.putExtra("violence", movie!!.violence)
		intent.putExtra("languageUsed", movie!!.languageUsed)

		if (item.itemId == R.id.submitReview) {
			val reviewText = findViewById<EditText>(R.id.commentET).text.toString()
			val reviewStars = findViewById<RatingBar>(R.id.stars).rating

			val db = DbAdapter(applicationContext)
			db.open()
			db.addReview(movie!!._id, reviewText, reviewStars)
			db.close()

			intent.putExtra("comment", reviewText)
			intent.putExtra("stars", reviewStars)
		}

		startActivity(intent)
		return super.onOptionsItemSelected(item)
	}
}