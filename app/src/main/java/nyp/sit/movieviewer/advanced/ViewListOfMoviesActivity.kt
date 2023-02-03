package nyp.sit.movieviewer.advanced

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import nyp.sit.movieviewer.advanced.entity.MovieItem

class ViewListOfMoviesActivity : AppCompatActivity() {
    private lateinit var moviesViewModel: MoviesListViewModel
    private val movies = mutableListOf<MovieItem>()

    private var state: Parcelable? = null

    val SHOW_BY_TOP_RATED = 1
    val SHOW_BY_POPULAR = 2

    private var displayType = SHOW_BY_TOP_RATED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)

        val appScope = CoroutineScope(SupervisorJob())
        val db by lazy { MoviesDb.getInstance(this, appScope) }
        val repo by lazy { MoviesRepository(db.MoviesDAO()) }
        moviesViewModel = ViewModelProvider(this, MoviesViewModelFactory(repo))[MoviesListViewModel::class.java]

        moviesViewModel.movies.observe(this, Observer {
            var movies = mutableListOf<MovieItem>()
            for (movie in it) movies.add(movie)
            it?.let { movielist.adapter = ListViewAdapter(this, movies) }
        })

        movielist.setOnItemClickListener { adapter, _, index, _ ->
            val intent = Intent(this, ItemDetailActivity::class.java)
            intent.putExtra("id", (adapter.getItemAtPosition(index) as MovieItem).id)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.w("Fetch Movies", "Getting Movies")
        loadMovieData(displayType)
        Log.w("Fetch Movies", "Got Movies")
    }

    private fun loadMovieData(viewType: Int) {
        val showTypeStr = when (viewType) {
            SHOW_BY_POPULAR -> NetworkUtils.POPULAR_PARAM
            else -> NetworkUtils.TOP_RATED_PARAM
        }

        displayType = viewType

        movies.clear()
        GlobalScope.launch(Dispatchers.IO) {
            val url = NetworkUtils.buildUrl(showTypeStr, getString(R.string.moviedb_api_key))!!
            val res = NetworkUtils.getResponseFromHttpUrl(url) ?: return@launch
            val data = movieDBJsonUtils.getMovieDetailsFromJson(this@ViewListOfMoviesActivity, res) ?: return@launch
            data.let {
                moviesViewModel.deleteAll()
                moviesViewModel.insertAll(it)
                for (movie in it) movies.add(movie)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.sortPopular -> loadMovieData(SHOW_BY_POPULAR)
            R.id.sortTopRated -> loadMovieData(SHOW_BY_TOP_RATED)
            R.id.viewFav -> viewFav()
            R.id.signOut -> signOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun viewFav() {
        val intent = Intent(this, FavouriteMovies::class.java)
        startActivity(intent)
    }

    private fun signOut() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
//        AWSMobileClient.getInstance().signOut(
//            SignOutOptions.Builder().signOutGlobally(false).build(),
//            object: Callback<Void> {
//                override fun onResult(result: Void?) {
//                    val intent = Intent(this@ViewListOfMoviesActivity, Login::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//
//                override fun onError(e: Exception?) { Log.d("Auth", e?.message.toString()) }
//            }
//        )
    }
}