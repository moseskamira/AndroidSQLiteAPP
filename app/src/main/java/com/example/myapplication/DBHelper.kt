package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper {

    constructor(context: Context?) : super(context, Constants.DB_NAME, null, Constants.BD_VERSION)

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Constants.CREATE_TB)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS"+Constants.TB_NAME)
        onCreate(db)
    }
}