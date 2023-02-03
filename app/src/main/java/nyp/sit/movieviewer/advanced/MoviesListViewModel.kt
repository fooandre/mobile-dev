package nyp.sit.movieviewer.advanced

import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nyp.sit.movieviewer.advanced.entity.MovieItem

class MoviesListViewModel(private val repo: MoviesRepository) : ViewModel() {
	public val movies: LiveData<List<MovieItem>> = repo.movies.asLiveData()

	fun insertAll(movies: ArrayList<MovieItem>) = viewModelScope.launch(Dispatchers.IO) { repo.insertAll(movies) }
	fun getMovie(id: Int) = viewModelScope.launch(Dispatchers.IO) { repo.getMovie(id) }
	fun deleteAll() = viewModelScope.launch(Dispatchers.IO) { repo.deleteAll() }
}