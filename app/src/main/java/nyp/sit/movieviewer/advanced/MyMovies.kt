package nyp.sit.movieviewer.advanced

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyMovies() : Application() {
	private val appScope = CoroutineScope(SupervisorJob())
	private val db by lazy { MoviesDb.getInstance(this, appScope) }
	val repo by lazy { MoviesRepository(db.MoviesDAO()) }
}