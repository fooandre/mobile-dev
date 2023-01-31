package nyp.sit.movieviewer.basic

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import nyp.sit.movieviewer.basic.data.ListViewAdapter
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem

class SimpleViewListOfMoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)

        val listView = findViewById<ListView>(R.id.movielist)
        listView.adapter = ListViewAdapter(this, SimpleMovieSampleData.simpleMovieitemArray)
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, SimpleItemDetailActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movies_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navigateTo = when (item.itemId) {
            R.id.viewFavorites -> FavMovies::class.java
            else -> Login::class.java
        }

        val intent = Intent(this, navigateTo)
        startActivity(intent)

        return super.onOptionsItemSelected(item)
    }
}