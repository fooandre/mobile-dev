package nyp.sit.movieviewer.intermediate

import nyp.sit.movieviewer.intermediate.entity.MovieItem

class MoviesRepository(private val moviesDAO: MoviesDAO) {
	val movies = moviesDAO.getMovies()

	suspend fun insert(movie: MovieItem) { moviesDAO.insert(movie) }
}