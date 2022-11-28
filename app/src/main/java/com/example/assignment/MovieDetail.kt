package com.example.assignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.RadioButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MovieDetail : AppCompatActivity() {
	private var violence = false
	private var languageUsed = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_movie_detail)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		findViewById<TextView>(R.id.titleTV).text = intent.getStringExtra("title")
		findViewById<TextView>(R.id.descTV).text = intent.getStringExtra("description")
		findViewById<TextView>(R.id.languageTV).text = intent.getStringExtra("language")
		findViewById<TextView>(R.id.dateTV).text = intent.getStringExtra("date")
		violence = intent.getBooleanExtra("violence", false)
		violence = intent.getBooleanExtra("languageUsed", false)

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

		if (intent.getStringExtra("comment") != null && intent.getStringExtra("comment")!!.isNotBlank()) {
			findViewById<TextView>(R.id.noReviewsTV).visibility = View.GONE
			findViewById<LinearLayout>(R.id.reviewLL).visibility = View.VISIBLE
			findViewById<RatingBar>(R.id.rating).rating = intent.getFloatExtra("stars", 0f)
			findViewById<TextView>(R.id.reviewTV).text = intent.getStringExtra("comment")
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return super.onSupportNavigateUp()
	}

	fun goToReview(item: MenuItem) {
		var intent = Intent(this, RatingActivity::class.java)
		intent.putExtra("title", findViewById<TextView>(R.id.titleTV).text.toString())
		intent.putExtra("description", findViewById<TextView>(R.id.descTV).text.toString())
		intent.putExtra("date", findViewById<TextView>(R.id.dateTV).text.toString())
		intent.putExtra("language",findViewById<TextView>(R.id.languageTV).text.toString())
		intent.putExtra("violence", violence)
		intent.putExtra("languageUsed", languageUsed)
		startActivity(intent)
	}
}