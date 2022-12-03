package com.example.assignment.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbAdapter(context: Context) {
    private val _dbVersion = 1
    private var _db: SQLiteDatabase? = null
    private val _dbName = "movieRater.db"
    private val _tableName = "movies"
    private val _dbCreate = ("create table $_tableName " + "(" +
            "_id integer primary key autoincrement, " +
            "title text not null, " +
            "description text not null, " +
            "releaseDate text not null, " +
            "violence integer not null, " +
            "languageUsed integer not null, " +
            "language text not null, " +
            "reviewText text, " +
            "reviewStars real);")
    private var dbHelper: DbHelper? = null
    init { dbHelper = DbHelper(context, _dbName, _dbVersion) }

    fun open() {
        _db = try {
            dbHelper?.writableDatabase
        } catch(e: SQLiteException) { dbHelper?.readableDatabase }
    }

    fun close() { _db?.close() }

    private fun Boolean.toInt() = if (this) 1 else 0

    fun getAll(): Cursor? {
        var cursor: Cursor? = null
        try {
            cursor = _db?.query(_tableName, null, null, null, null, null, null)
        } catch(e: SQLiteException) { Log.w(_tableName,"Get movies failed") }
        return cursor
    }

    fun insert(title: String, description: String, releaseDate: String, violence: Boolean = false, languageUsed: Boolean = false, language: String = "English"): Long? {
        val newValues = ContentValues()
        newValues.put("title", title)
        newValues.put("description", description)
        newValues.put("releaseDate", releaseDate)
        newValues.put("violence", violence.toInt())
        newValues.put("languageUsed", languageUsed.toInt())
        newValues.put("language", language)
        return _db?.insert(_tableName, null, newValues)
    }

    inner class DbHelper(context: Context, dbName: String, versionNum: Int) : SQLiteOpenHelper(context, dbName, null, versionNum) {
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(_dbCreate)
            Log.w("DB", "Table Created")
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    }
}