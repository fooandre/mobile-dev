package nyp.sit.movieviewer.intermediate

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nyp.sit.movieviewer.intermediate.entity.MovieItem

@Dao
interface MoviesDAO {
	@Query("select * from movies")
	fun getMovies() : Flow<List<MovieItem>>

	@Query("select * from movies where id = :id")
	fun getMovie(id: Int) : MovieItem?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(movie: MovieItem)

	@Delete
	fun delete(movie: MovieItem)

	@Query("delete from movies")
	fun deleteAll()
}