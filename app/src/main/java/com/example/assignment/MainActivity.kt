package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

//		setSupportActionBar(findViewById(R.id.toolbar))
	}

	fun goToMovie(view: View) {
		var intent = Intent(this, AddMovie::class.java)
		startActivity(intent)
		finish()
	}
}