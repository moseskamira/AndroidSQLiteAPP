package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DBAdapter(private val context: Context) {
    lateinit var db: SQLiteDatabase
    private val helper: DBHelper = DBHelper(context)

    // Opening Database
    fun openDB(): DBAdapter{
        db = helper.writableDatabase
        return this
    }

    // Closing Database
    fun closeDB(){
        helper.close()
    }

    // Inserting Data Into Database
    fun inserData(name: String, position: String): Long{
        val cv = ContentValues()
        cv.put(Constants.NAME, name)
        cv.put(Constants.POSITION, position)
        return db.insert(Constants.TB_NAME, Constants.ROW_ID, cv)
    }

    // Retrieving All Players
    fun getAllPlayers(): Cursor{
        val columns= arrayOf(Constants.ROW_ID, Constants.NAME, Constants.POSITION)
        return  db.query(Constants.TB_NAME, columns, null, null, null, null, null)
    }
}