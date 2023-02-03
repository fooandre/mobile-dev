package nyp.sit.movieviewer.advanced

import nyp.sit.movieviewer.advanced.entity.UserData

class UserDataRepository(private val userDataDAO: UserDataDAO) {
	val movies = userDataDAO.getMovies()

	suspend fun insert(movie: UserData) { userDataDAO.insert(movie) }
	suspend fun getMovies(user: String) { userDataDAO.getMovies(user) }
}