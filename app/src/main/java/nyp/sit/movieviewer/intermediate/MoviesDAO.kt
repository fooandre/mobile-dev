package nyp.sit.movieviewer.intermediate

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nyp.sit.movieviewer.intermediate.entity.MovieItem

@Dao
interface MoviesDAO {
	@Query("select * from movies")
	fun getMovies() : Flow<List<MovieItem>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(movie: MovieItem)
}