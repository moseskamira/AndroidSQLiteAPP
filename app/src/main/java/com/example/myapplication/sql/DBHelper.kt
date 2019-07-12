package com.example.myapplication.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper {

    companion object{
        const val DB_NAME = "player.db"
        const val TB_NAME = "player_table"
        const val DB_VERSION = 1
        const val col_1 = "ID"
        const val col_2 = "NAME"
        const val col_3 = "POSITION"
    }

    constructor(context: Context?) : super(context, DB_NAME, null, DB_VERSION)
//    val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE $TB_NAME($col_1 INTEGER PRIMARY KEY AUTOINCREMENT, $col_2 TEXT NOT NULL, $col_3 TEXT NOT NULL)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TB_NAME")
        onCreate(db)
    }
}