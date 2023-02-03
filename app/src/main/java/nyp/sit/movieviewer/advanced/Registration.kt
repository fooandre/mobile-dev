package nyp.sit.movieviewer.advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler
import com.amazonaws.services.cognitoidentityprovider.model.SignUpResult
import kotlinx.android.synthetic.main.activity_registration.*
import java.lang.Exception

class Registration : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_registration)
	}

	fun register(view: View) {
		var valid = true

		if (loginName.text.toString().isNullOrBlank()) {
			loginName.error = "Login Name cannot be empty."
			valid = false
		}

		val passwordInp = password.text.toString()

		var passwordErr = when {
			passwordInp.isNullOrBlank() -> "Password cannot be empty."
			passwordInp.length < 8 -> "Password must be at least 8 characters in length."
			!passwordInp.contains("\\d".toRegex()) -> "Password must contain a number."
			!passwordInp.contains("[A-Z]".toRegex()) -> "Password must contain an uppercase letter."
			!passwordInp.contains("[a-z]".toRegex()) -> "Password must contain an lowercase letter."
			else -> null
		}

		if (passwordErr != null) {
			password.error = passwordErr
			valid = false
		}

		if (email.text.toString().isNullOrBlank()) {
			email.error = "Email cannot be empty."
			valid = false
		}

		if (adminNumber.text.toString().isNullOrBlank()) {
			adminNumber.error = "Admin Number cannot be empty."
			valid = false
		}

		if (pemGroup.text.toString().isNullOrBlank()) {
			pemGroup.error = "PEM Group cannot be empty."
			valid = false
		}

		if (!valid) return

//		val userPool = CognitoUserPool(view.context, AWSMobileClient.getInstance().configuration)
//
//		val userAttrs = CognitoUserAttributes()
//		userAttrs.addAttribute("email", email.text.toString())
//		userAttrs.addAttribute("custom:AdminNumber", adminNumber.text.toString())
//		userAttrs.addAttribute("custom:PemGrp", pemGroup.text.toString())
//
//		userPool.signUp(loginName.text.toString(),
//			passwordInp,
//			userAttrs,
//			null,
//			object : SignUpHandler {
//				override fun onSuccess(user: CognitoUser?, signUpResult: SignUpResult?) { Log.d("Urmom", "Sign up success: ${signUpResult?.userConfirmed}") }
//				override fun onFailure(exception: Exception?) { Log.d("HiBye", exception?.message.toString()) }
//			})

		Toast.makeText(applicationContext, "Login Name: ${loginName.text}\nPassword: ${password.text}\nEmail: ${email.text}\nAdmin Number: ${adminNumber.text}\nPEM Group : ${pemGroup.text}", Toast.LENGTH_SHORT).show()
		val intent = Intent(this, Login::class.java)
		startActivity(intent)
	}
}