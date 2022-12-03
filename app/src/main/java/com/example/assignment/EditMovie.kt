package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.models.DbAdapter
import com.example.assignment.models.Language
import com.example.assignment.models.Movie

class EditMovie : AppCompatActivity() {
	private var id: Long? = null
	private var violence = false
	private var languageUsed = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_edit_movie)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		id = intent.getLongExtra("id", -1)
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

		if (intent.getBooleanExtra("languageUsed", true)) {
			findViewById<CheckBox>(R.id.showLayoutCB).isChecked = true
			findViewById<LinearLayout>(R.id.checkboxes).visibility = View.VISIBLE
			findViewById<CheckBox>(R.id.languageCB).isChecked = true
		}
	}

	override fun onNavigateUp(): Boolean {
		onBackPressed()
		return super.onNavigateUp()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.edit_movie_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.saveChanges) {
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

			if (!valid) return true

			var title = findViewById<EditText>(R.id.nameET).text.toString()
			var description = findViewById<EditText>(R.id.descET).text.toString()
			var language =
				if (findViewById<RadioButton>(R.id.englishRB).isChecked) "English" else if (findViewById<RadioButton>(
						R.id.chineseRB).isChecked) "Chinese" else if (findViewById<RadioButton>(R.id.malayRB).isChecked) "Malay" else "Tamil"
			var date = findViewById<EditText>(R.id.dateET).text.toString()

			val db = DbAdapter(applicationContext)
			db.open()
			db.updateMovie(id!!, title, description, date, violence, languageUsed, language)
			db.close()

			val intent = Intent(this, MovieDetail::class.java)
			intent.putExtra("id", id)
			intent.putExtra("title", title)
			intent.putExtra("description", description)
			intent.putExtra("language", language)
			intent.putExtra("date", date)
			intent.putExtra("violence", violence)
			intent.putExtra("languageUsed", languageUsed)
			startActivity(intent)
		}

		onNavigateUp()
		return super.onOptionsItemSelected(item)
	}

	fun showCheckboxes(view: View) {
		findViewById<LinearLayout>(R.id.checkboxes).visibility =
			if ((view as CheckBox).isChecked) View.VISIBLE else View.INVISIBLE
	}
}