package com.example.assignment

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddMovie : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_add_movie)
		findViewById<RadioButton>(R.id.englishRB).isChecked = true
	}

	fun showCheckboxes(view: View) {
		findViewById<LinearLayout>(R.id.checkboxes).visibility =
			if ((view as CheckBox).isChecked) View.VISIBLE else View.INVISIBLE
	}

	fun addMovie(view: View) {
		var valid = true

		if (findViewById<EditText>(R.id.nameET).text.toString().isBlank()) {
			findViewById<EditText>(R.id.nameET).error = "Title of movie cannot be empty."
			valid = false
		}

		if (findViewById<EditText>(R.id.descET).text.toString().isBlank()) {
			findViewById<EditText>(R.id.descET).error = "Description of movie cannot be empty."
			valid = false
		}

		if (findViewById<EditText>(R.id.dateET).text.toString().isBlank()) {
			findViewById<EditText>(R.id.dateET).error = "Release date of movie cannot be empty."
			valid = false
		}

		if (findViewById<LinearLayout>(R.id.checkboxes).visibility == View.VISIBLE && !(findViewById<CheckBox>(
				R.id.violenceCB
			).isChecked || findViewById<CheckBox>(R.id.languageCB).isChecked)
		) {
			Toast.makeText(
				applicationContext,
				"Please select a reason for not suitable for all audience",
				Toast.LENGTH_SHORT
			).show()
			valid = false
		}

		if (!valid) return

		var title = findViewById<EditText>(R.id.nameET).text
		var description = findViewById<EditText>(R.id.descET).text
		var language =
			if (findViewById<RadioButton>(R.id.englishRB).isChecked) "English" else if (findViewById<RadioButton>(
					R.id.chineseRB
				).isChecked
			) "Chinese" else if (findViewById<RadioButton>(R.id.malayRB).isChecked) "Malay" else "Tamil"
		var date = findViewById<EditText>(R.id.dateET).text
		var suitableForAll = findViewById<LinearLayout>(R.id.checkboxes).visibility == View.INVISIBLE
		var text =
			"Title = $title\nOverview = $description\nRelease Date = $date\nLanguage = $language\nNot suitable for all ages = ${!suitableForAll}"

		if (!suitableForAll) {
			if (findViewById<CheckBox>(R.id.violenceCB).isChecked) text += "\nViolence"
			if (findViewById<CheckBox>(R.id.languageCB).isChecked) text += "\nLanguage"
		}
		Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
	}
}