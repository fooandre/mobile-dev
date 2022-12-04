package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.DbAdapter

class MovieDetail : AppCompatActivity() {
	private var id: Long? = null
	private var violence = false
	private var languageUsed = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_movie_detail)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		id = intent.getLongExtra("id", -1)
		findViewById<TextView>(R.id.titleTV).text = intent.getStringExtra("title")
		findViewById<TextView>(R.id.descTV).text = intent.getStringExtra("description")
		findViewById<TextView>(R.id.languageTV).text = intent.getStringExtra("language")
		findViewById<TextView>(R.id.dateTV).text = intent.getStringExtra("date")
		violence = intent.getBooleanExtra("violence", false)
		languageUsed = intent.getBooleanExtra("languageUsed", false)

		var reason = mutableListOf<String>()
		if (violence) reason.add("Violence")
		if (languageUsed) reason.add("Language")
		findViewById<TextView>(R.id.suitableTV).text = if (violence || languageUsed) "No (${reason.joinToString(" & ")})" else "Yes"

		val tv = findViewById<TextView>(R.id.noReviewsTV)
		tv.setOnLongClickListener {
			val popup = PopupMenu(this, findViewById<TextView>(R.id.noReviewsTV))
			popup.menuInflater.inflate(R.menu.movie_detail_menu, popup.menu)
			popup.show()
			true
		}

		val db = DbAdapter(applicationContext)
		db.open()
		val reviewText = db.getReviewText(id!!)
		if (reviewText != null) {
			findViewById<TextView>(R.id.noReviewsTV).visibility = View.GONE
			findViewById<LinearLayout>(R.id.reviewLL).visibility = View.VISIBLE
			findViewById<TextView>(R.id.reviewTV).text = reviewText
			findViewById<RatingBar>(R.id.rating).rating = db.getReviewStars(id!!)
		}
		db.close()
	}

	override fun onSupportNavigateUp(): Boolean {
		var intent = Intent(this, MoviesList::class.java)
		startActivity(intent)
		return super.onSupportNavigateUp()
	}

	fun goToReview(item: MenuItem) {
		var intent = Intent(this, RatingActivity::class.java)
		intent.putExtra("id", id)
		intent.putExtra("title", findViewById<TextView>(R.id.titleTV).text.toString())
		intent.putExtra("description", findViewById<TextView>(R.id.descTV).text.toString())
		intent.putExtra("date", findViewById<TextView>(R.id.dateTV).text.toString())
		intent.putExtra("language",findViewById<TextView>(R.id.languageTV).text.toString())
		intent.putExtra("violence", violence)
		intent.putExtra("languageUsed", languageUsed)
		startActivity(intent)
	}
}