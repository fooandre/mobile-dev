package nyp.sit.movieviewer.intermediate

import nyp.sit.movieviewer.intermediate.entity.MovieItem

class MoviesRepository(private val moviesDAO: MoviesDAO) {
	val movies = moviesDAO.getMovies()

	suspend fun insertAll(movies: ArrayList<MovieItem>) { for (movie in movies) moviesDAO.insert(movie) }
	suspend fun getMovie(id: Int) { moviesDAO.getMovie(id) }
	suspend fun deleteAll() { moviesDAO.deleteAll() }
}