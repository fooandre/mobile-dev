package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
	private val _name = "testuser"
	private val _password = "testuser"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
	}

	fun login(view: View) {
		val name = findViewById<EditText>(R.id.loginName).text.toString()
		val password = findViewById<EditText>(R.id.password).text.toString()

		if (name != _name || password != _password) Toast.makeText(applicationContext, "Login Error", Toast.LENGTH_SHORT).show()
		else {
			val intent = Intent(this, SimpleViewListOfMoviesActivity::class.java)
			startActivity(intent)
		}
	}

	fun register(view: View) {
		val intent = Intent(this, Registration::class.java)
		startActivity(intent)
	}
}