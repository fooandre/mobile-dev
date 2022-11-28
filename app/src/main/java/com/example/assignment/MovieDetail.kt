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

class MovieDetail : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_movie_detail)

		findViewById<TextView>(R.id.titleTV).text = intent.getStringExtra("title")
		findViewById<TextView>(R.id.descTV).text = intent.getStringExtra("description")
		findViewById<TextView>(R.id.languageTV).text = intent.getStringExtra("language")
		findViewById<TextView>(R.id.dateTV).text = intent.getStringExtra("date")

		var reason = mutableListOf<String>()
		if (intent.getBooleanExtra("violence", false)) reason.add("Violence")
		if (intent.getBooleanExtra("languageUsed", false)) reason.add("Language")
		findViewById<TextView>(R.id.suitableTV).text =
			if (intent.getBooleanExtra("violence", false) || intent.getBooleanExtra("languageUsed",
					false)) "No (${reason.joinToString(" & ")})" else "Yes"

		val tv = findViewById<TextView>(R.id.noReviewsTV)
		tv.setOnLongClickListener {
			val popup = PopupMenu(this, findViewById<TextView>(R.id.noReviewsTV))
			popup.menuInflater.inflate(R.menu.movie_detail_menu, popup.menu)
			popup.show()
			true
		}
	}

	fun goToReview(item: MenuItem) {
		var intent = Intent(this, RatingActivity::class.java)
		intent.putExtra("title", findViewById<TextView>(R.id.titleTV).text.toString())
		intent.putExtra("description", findViewById<EditText>(R.id.descET).text.toString())
		intent.putExtra("date", findViewById<EditText>(R.id.dateET).text.toString())
		intent.putExtra("language",
			if (findViewById<RadioButton>(R.id.englishRB).isChecked) "English" else if (findViewById<RadioButton>(
					R.id.chineseRB).isChecked) "Chinese" else if (findViewById<RadioButton>(R.id.malayRB).isChecked) "Malay" else "Tamil")
		intent.putExtra("violence", findViewById<CheckBox>(R.id.violenceCB).isChecked)
		intent.putExtra("languageUsed", findViewById<CheckBox>(R.id.languageCB).isChecked)
		startActivity(intent)
	}
}