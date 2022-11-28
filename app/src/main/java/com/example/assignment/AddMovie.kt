package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.add_movie_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.addMovie) {
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
					Toast.makeText(applicationContext,
						"Please select a reason for not suitable for all audience",
						Toast.LENGTH_SHORT).show()
					valid = false
				}

				if (!valid) return true

				var intent = Intent(this, MovieDetail::class.java)
				intent.putExtra("title", findViewById<EditText>(R.id.nameET).text.toString())
				intent.putExtra("description", findViewById<EditText>(R.id.descET).text.toString())
				intent.putExtra("date", findViewById<EditText>(R.id.dateET).text.toString())
				intent.putExtra("language",
					if (findViewById<RadioButton>(R.id.englishRB).isChecked) "English" else if (findViewById<RadioButton>(
							R.id.chineseRB).isChecked) "Chinese" else if (findViewById<RadioButton>(R.id.malayRB).isChecked) "Malay" else "Tamil")
				intent.putExtra("violence", findViewById<CheckBox>(R.id.violenceCB).isChecked)
				intent.putExtra("languageUsed", findViewById<CheckBox>(R.id.languageCB).isChecked)
				startActivity(intent)
			} else {
				findViewById<EditText>(R.id.nameET).text.clear()
				findViewById<EditText>(R.id.descET).text.clear()
				findViewById<EditText>(R.id.dateET).text.clear()
				findViewById<RadioButton>(R.id.englishRB).isChecked = true

				findViewById<CheckBox>(R.id.suitableCB).isChecked = false
				findViewById<LinearLayout>(R.id.checkboxes).visibility = View.GONE
				findViewById<CheckBox>(R.id.violenceCB).isChecked = false
				findViewById<CheckBox>(R.id.languageCB).isChecked = false
			}

		return super.onOptionsItemSelected(item)
	}

	fun showCheckboxes(view: View) {
		findViewById<LinearLayout>(R.id.checkboxes).visibility =
			if ((view as CheckBox).isChecked) View.VISIBLE else View.INVISIBLE
	}
}