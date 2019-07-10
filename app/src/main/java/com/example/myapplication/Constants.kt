package com.example.myapplication

class Constants {

    companion object{
        // COLUMNS
        const val ROW_ID = "id"
        const val NAME = "name"
        const val POSITION = "position"

        // DB PROPERTIES
        const val DB_NAME = "db_name"
        const val TB_NAME = "tb_name"
        const val BD_VERSION = 1

       // const val CREATE_TB = "CREATE TABLE tb_name(id INTEGER PRIMARY KEY AUTOINCREMENT, "+ "name TEXT NOT NULL, position TEXT NOT NULL)"
        const val CREATE_TB = ("CREATE TABLE " + TB_NAME + " ("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT NOT NULL, "
                + POSITION + " TEXT NOT NULL" + ")")
    }



}