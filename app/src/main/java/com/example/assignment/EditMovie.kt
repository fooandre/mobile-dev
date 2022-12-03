package com.example.assignment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.Language
import com.example.assignment.models.Movie

class EditMovie : AppCompatActivity() {
	private var violence = false
	private var languageUsed = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit_movie)

		findViewById<TextView>(R.id.nameET).text = intent.getStringExtra("title")
		findViewById<TextView>(R.id.descET).text = intent.getStringExtra("description")
		findViewById<TextView>(R.id.dateET).text = intent.getStringExtra("date")
		violence = intent.getBooleanExtra("violence", false)
		languageUsed = intent.getBooleanExtra("languageUsed", false)

		when (intent.getStringExtra("language")) {
			"Chinese" -> findViewById<RadioButton>(R.id.chineseRB).isChecked = true
			"Malay" -> findViewById<RadioButton>(R.id.malayRB).isChecked = true
			"Tamil" -> findViewById<RadioButton>(R.id.tamilRB).isChecked = true
			else -> findViewById<RadioButton>(R.id.englishRB).isChecked = true
		}

		if (intent.getBooleanExtra("violence", false)) {
			findViewById<CheckBox>(R.id.showLayoutCB).isChecked = true
			findViewById<LinearLayout>(R.id.checkboxes).visibility = View.VISIBLE
			findViewById<CheckBox>(R.id.violenceCB).isChecked = true
		}

		if (intent.getBooleanExtra("violence", true)) {
			findViewById<CheckBox>(R.id.showLayoutCB).isChecked = true
			findViewById<LinearLayout>(R.id.checkboxes).visibility = View.VISIBLE
			findViewById<CheckBox>(R.id.languageCB).isChecked = true
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.edit_movie_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.saveChanges) {}
		if (item.itemId == R.id.cancelChanges) {}

		return super.onOptionsItemSelected(item)
	}

	fun showCheckboxes(view: View) {
		findViewById<LinearLayout>(R.id.checkboxes).visibility =
			if ((view as CheckBox).isChecked) View.VISIBLE else View.INVISIBLE
	}

	fun saveChanges(view: View) {
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
				R.id.violenceCB).isChecked || findViewById<CheckBox>(R.id.languageCB).isChecked)) {
			Toast.makeText(applicationContext,
				"Please select a reason for not suitable for all audience",
				Toast.LENGTH_SHORT).show()
			valid = false
		}

		if (!valid) return

		var title = findViewById<EditText>(R.id.nameET).text
		var description = findViewById<EditText>(R.id.descET).text
		var language =
			if (findViewById<RadioButton>(R.id.englishRB).isChecked) "English" else if (findViewById<RadioButton>(
					R.id.chineseRB).isChecked) "Chinese" else if (findViewById<RadioButton>(R.id.malayRB).isChecked) "Malay" else "Tamil"
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