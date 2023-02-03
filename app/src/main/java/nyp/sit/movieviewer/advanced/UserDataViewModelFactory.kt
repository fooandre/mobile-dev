package nyp.sit.movieviewer.advanced

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserDataViewModelFactory(private val repo: UserDataRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(UserDataViewModel::class.java)) return UserDataViewModel(repo) as T
		throw IllegalArgumentException("Unknown viewmodel class")
	}
}