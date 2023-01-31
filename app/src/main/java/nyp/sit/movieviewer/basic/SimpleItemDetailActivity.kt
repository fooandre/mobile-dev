package nyp.sit.movieviewer.basic

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import nyp.sit.movieviewer.basic.data.DatabaseAdapter
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem

class SimpleItemDetailActivity : AppCompatActivity() {
    private var _movie: SimpleMovieItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_activity_item_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val position = intent.getIntExtra("position", 0)
        _movie = SimpleMovieSampleData.simpleMovieitemArray[position]

        findViewById<TextView>(R.id.movie_title).text = _movie?.title
        findViewById<TextView>(R.id.movie_overview).text = _movie?.overview
        findViewById<TextView>(R.id.movie_release_date).text = _movie?.release_date
        findViewById<TextView>(R.id.movie_language).text = _movie?.original_langauge
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, SimpleViewListOfMoviesActivity::class.java)
        startActivity(intent)
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val db = DatabaseAdapter(applicationContext)
        db.saveMovie(_movie?.title!!, _movie?.overview!!, _movie?.release_date!!, _movie?.original_langauge!!)
        val intent = Intent(this, FavMovies::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}