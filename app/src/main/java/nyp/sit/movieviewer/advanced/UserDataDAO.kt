package nyp.sit.movieviewer.advanced

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nyp.sit.movieviewer.advanced.entity.UserData

@Dao
interface UserDataDAO {
	@Query("select * from favMovies where user like :user")
	fun getMovies(user: String) : Flow<List<UserData>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(movie: UserData)
}