package nyp.sit.movieviewer.advanced

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import nyp.sit.movieviewer.advanced.entity.*

@Database(entities = [MovieItem::class], version = 1, exportSchema = false)
abstract class MoviesDb : RoomDatabase() {
	abstract fun MoviesDAO(): MoviesDAO

	companion object {
		@Volatile
		private var INSTANCE: MoviesDb? = null

		fun getInstance(context: Context, scope: CoroutineScope) : MoviesDb {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(context, MoviesDb::class.java, "movies_db").build()
				INSTANCE = instance
				instance
			}
		}
	}
}