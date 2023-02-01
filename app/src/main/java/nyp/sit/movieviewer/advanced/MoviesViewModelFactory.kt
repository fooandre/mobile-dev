package nyp.sit.movieviewer.intermediate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MoviesViewModelFactory(private val repo: MoviesRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) return MoviesListViewModel(repo) as T
		throw IllegalArgumentException("Unknown viewmodel class")
	}
}