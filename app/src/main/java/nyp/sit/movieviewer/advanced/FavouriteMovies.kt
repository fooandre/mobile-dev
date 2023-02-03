package nyp.sit.movieviewer.advanced

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FavouriteMovies : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_favourite_movies)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return super.onSupportNavigateUp()
	}
}