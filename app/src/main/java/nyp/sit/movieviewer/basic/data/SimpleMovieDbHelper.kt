package nyp.sit.movieviewer.basic.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SimpleMovieDbHelper(c: Context, private val dbCreate: String) : SQLiteOpenHelper(c, DB_NAME, null, 1) {

    companion object {
        val DB_NAME = "simplemovie.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(dbCreate)
        Log.w("DB Helper", "DB $DB_NAME Created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}