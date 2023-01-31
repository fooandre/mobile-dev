package nyp.sit.movieviewer.intermediate

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.room.InvalidationTracker
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nyp.sit.movieviewer.intermediate.entity.MovieItem

class ViewListOfMoviesActivity : AppCompatActivity() {

    private val viewModel: MoviesListViewModel by viewModels() { MoviesViewModelFactory((application as MyMovies).repo) }

    val SHOW_BY_TOP_RATED = 1
    val SHOW_BY_POPULAR = 2

    private var displayType = SHOW_BY_TOP_RATED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)

//        viewModel.insert(MovieItem(adult = false,
//            title = "ur mom",
//            backdrop_path = "",
//            genre_ids = "",
//            original_language = "English",
//            original_title = "bruh",
//            overview = "no",
//            poster_path = "",
//            release_date = "20-12-2020",
//            video = true))

        viewModel.movies.observe(this, Observer {
            var movies = mutableListOf<MovieItem>()
            for (movie in it) movies.add(movie)
            it?.let { movielist.adapter = ArrayAdapter(this, R.layout.movie_item, R.id.movieName, movies) }
        })
    }

    override fun onStart() {
        super.onStart()
        loadMovieData(displayType)
    }

    private fun loadMovieData(viewType: Int) {
        val showTypeStr = when (viewType) {
            SHOW_BY_POPULAR -> NetworkUtils.POPULAR_PARAM
            else -> NetworkUtils.TOP_RATED_PARAM
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
        }
        return super.onOptionsItemSelected(item)
    }

}