package nyp.sit.movieviewer.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class VerificationCode : AppCompatActivity() {
	private val _verificationCode = "1234"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_verification_code)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return super.onSupportNavigateUp()
	}

	fun verify(view: View) {
		val verificationCode = findViewById<EditText>(R.id.verificationCode)
		if (verificationCode.text.isNullOrBlank()) {
			verificationCode.error = "Verification Code cannot be empty."
			return
		}

		if (verificationCode.text.toString() != _verificationCode) {
			Toast.makeText(applicationContext, "Code Error", Toast.LENGTH_SHORT).show()
			return
		}

		Toast.makeText(applicationContext, "Code verified", Toast.LENGTH_SHORT).show()
		val intent = Intent(this, Login::class.java)
		startActivity(intent)
	}
}