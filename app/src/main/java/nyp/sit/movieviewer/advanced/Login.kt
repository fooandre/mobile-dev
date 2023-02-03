package nyp.sit.movieviewer.advanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.UserStateDetails
import com.amazonaws.mobile.client.results.SignInResult
import com.amazonaws.mobile.client.results.SignInState
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class Login : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		AWSMobileClient.getInstance().initialize(this, object: Callback<UserStateDetails> {
			override fun onResult(result: UserStateDetails?) { Log.d("Auth", result?.userState?.name.toString()) }
			override fun onError(e: Exception?) { Log.d("Auth", e.toString()) }
		})
	}

	fun login(view: View) {
		if (loginName.text.toString() != "testuser" || password.text.toString() != "testuser") {
			Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show()
			return
		}

		val intent = Intent(this, ViewListOfMoviesActivity::class.java)
		startActivity(intent)

//		CoroutineScope(Job() + Dispatchers.IO)?.launch {
//			AWSMobileClient.getInstance().signIn(
//				loginName.text.toString(),
//				password.text.toString(),
//				null,
//				object: Callback<SignInResult> {
//					override fun onResult(result: SignInResult?) {
//						Log.d("Auth", "Sign in result: ${result.toString()}")
//						if (result?.signInState == SignInState.DONE) {
//							val intent = Intent(view.context, ViewListOfMoviesActivity::class.java)
//							startActivity(intent)
//						}
//					}
//
//					override fun onError(e: Exception?) {
//						Log.d("Auth", e?.message.toString())
//						GlobalScope.launch(Dispatchers.Main) { Toast.makeText(view.context, "Login Error", Toast.LENGTH_SHORT).show() }
//					}
//				}
//			)
//		}
	}

	fun register(view: View) {
		val intent = Intent(this, Registration::class.java)
		startActivity(intent)
	}
}