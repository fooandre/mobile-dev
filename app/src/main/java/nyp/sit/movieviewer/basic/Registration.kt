package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class Registration : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_registration)
	}

	fun register(view: View) {
		var valid = true

		var loginName = findViewById<EditText>(R.id.loginName)
		if (loginName.text.toString().isNullOrBlank()) {
			loginName.error = "Login Name cannot be empty."
			valid = false
		}

		var password = findViewById<EditText>(R.id.password)
		if (password.text.toString().isNullOrBlank())
		{
			password.error = "Password cannot be empty."
			valid = false
		}

		var email = findViewById<EditText>(R.id.email)
		if (email.text.toString().isNullOrBlank()) {
			email.error = "Email cannot be empty."
			valid = false
		}

		var adminNumber = findViewById<EditText>(R.id.adminNumber)
		if (adminNumber.text.toString().isNullOrBlank()) {
			adminNumber.error = "Admin Number cannot be empty."
			valid = false
		}

		var pemGroup = findViewById<EditText>(R.id.pemGroup)
		if (pemGroup.text.toString().isNullOrBlank()) {
			pemGroup.error = "PEM Group cannot be empty."
			valid = false
		}

		if (!valid) return

		Toast.makeText(applicationContext, "Login Name: ${loginName.text}\nPassword: ${password.text}\nEmail: ${email.text}\nAdmin Number: ${adminNumber.text}\nPEM Group : ${pemGroup.text}", Toast.LENGTH_SHORT).show()
		val intent = Intent(this, VerificationCode::class.java)
		startActivity(intent)
	}
}