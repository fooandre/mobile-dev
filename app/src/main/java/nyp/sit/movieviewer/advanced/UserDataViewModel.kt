package nyp.sit.movieviewer.advanced

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nyp.sit.movieviewer.advanced.entity.MovieItem
import nyp.sit.movieviewer.advanced.entity.UserData

class UserDataViewModel(private val repo: UserDataRepository) : ViewModel() {
	public val movies: LiveData<List<UserData>> = repo.movies.asLiveData()

	fun insert(movie: UserData) = viewModelScope.launch(Dispatchers.IO) { repo.insert(movie) }
	fun getMovies(user: String) = viewModelScope.launch(Dispatchers.IO) { repo.getMovies(user) }
}