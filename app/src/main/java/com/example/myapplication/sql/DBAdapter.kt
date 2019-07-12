package com.example.myapplication.sql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DBAdapter(private val context: Context) {
    lateinit var db: SQLiteDatabase
    private val helper: DBHelper = DBHelper(context)

    // Opening Database
    fun openDB(): DBAdapter {
        db = helper.writableDatabase
        return this
    }

    // Closing Database
    fun closeDB(){
        helper.close()
    }

    // Inserting Data Into Database
    fun insertData(name: String, position: String): Boolean{
        val cv = ContentValues()
        cv.put(DBHelper.col_2, name)
        cv.put(DBHelper.col_3, position)
        val result: Long = db.insert(DBHelper.TB_NAME, null, cv)
        if (result > 0) {
            return true
        }
        return false
    }

    // Retrieving All Players
    fun getAllPlayers(): Cursor{
        val columns= arrayOf(DBHelper.col_1, DBHelper.col_2, DBHelper.col_3)
        return  db.query(DBHelper.TB_NAME, columns, null, null, null, null, null)
    }
}