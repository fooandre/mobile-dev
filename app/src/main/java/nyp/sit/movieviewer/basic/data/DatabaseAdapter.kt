package nyp.sit.movieviewer.basic.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException

class DatabaseAdapter(private val context: Context) {
	private val DB_TABLE = "favorites"
	private val DB_CREATE = """create table $DB_TABLE
		|(_id integer primary key autoincrement,
		|title text not null,
		|overview text not null,
		|releaseDate text not null,
		|language text not null);""".trimMargin()

	private var dbHelper = SimpleMovieDbHelper(context, DB_CREATE)

	fun getMovies(): Cursor? {
		return try {
			dbHelper.readableDatabase.query(DB_TABLE, arrayOf("_id", "title", "overview", "releaseDate", "language"), null, null, null, null, null)
		} catch (error: SQLiteException) { null }
	}

	fun saveMovie(title: String, overview: String, releaseDate: String, language: String): Long? {
		val toAdd = ContentValues()
		toAdd.put("title", title)
		toAdd.put("overview", overview)
		toAdd.put("releaseDate", releaseDate)
		toAdd.put("language", language)

		return dbHelper.writableDatabase.insert(DB_TABLE, null, toAdd)
	}
}