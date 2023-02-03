package nyp.sit.movieviewer.advanced

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import nyp.sit.movieviewer.advanced.entity.MovieItem
import nyp.sit.movieviewer.advanced.entity.UserData

class ItemDetailActivity : AppCompatActivity() {

    private var movie: MovieItem? = null
    private lateinit var viewModel: UserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val appScope = CoroutineScope(SupervisorJob())
        val userDataDb by lazy { MoviesDb.getInstance(this, appScope) }
        val userDataRepo by lazy { UserDataRepository(userDataDb.MoviesDAO()) }
        viewModel = ViewModelProvider(this, UserDataViewModelFactory(userDataRepo))[UserDataViewModel::class.java]

        val db by lazy { MoviesDb.getInstance(this, appScope) }
        val dao = db.MoviesDAO()

        var res = GlobalScope.async (Dispatchers.Default) { dao.getMovie(intent.getIntExtra("id", 0)) }

        GlobalScope.launch {
            movie = res.await()
            movie_overview.text = movie?.overview
            movie_release_date.text = movie?.release_date
            movie_popularity.text = movie?.popularity.toString()
            movie_vote_count.text = movie?.vote_count.toString()
            movie_vote_avg.text = movie?.vote_average.toString()
            movie_language.text = movie?.original_language
            movie_is_adult.text = movie?.adult.toString()
            movie_hasvideo.text = movie?.video.toString()
        }

        MainScope().launch {
            Log.w("Fetch Details", "Getting Poster")
            val imageUrl = NetworkUtils.buildImageUrl(movie?.poster_path)
            Picasso.get().load(imageUrl?.toString()).into(posterIV)
            Log.w("Fetch Details", "Got Poster")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}