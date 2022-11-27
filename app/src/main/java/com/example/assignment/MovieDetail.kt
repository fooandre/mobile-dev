package com.example.assignment

import android.os.Bundle
import android.widget.TextView
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
					false)) "No (${reason.joinToString("&")})" else "Yes"
	}
}