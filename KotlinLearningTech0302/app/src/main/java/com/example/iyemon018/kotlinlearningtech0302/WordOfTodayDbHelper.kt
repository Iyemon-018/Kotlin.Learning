package com.example.iyemon018.kotlinlearningtech0302

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by iyemon018 on 2018/11/14.
 */
class WordOfTodayDbHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int,
    errorHandler: DatabaseErrorHandler?
) : SQLiteOpenHelper(context, name, factory, version, errorHandler) {

    companion object {
        const val CREATE_TABLE_QUERY: String = "CREATE TABLE WordsOfToday (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "words TEXT," +
                "date TEXT" +
                ");"
        val DEFAULT_DATA_LIST = arrayOf(
            "INSERT INTO WordsOfToday (name, words, date) VALUES('Taiki', '今日はいい天気', 20180101);"
            , "INSERT INTO WordsOfToday (name, words, date) VALUES('Taiki', '今日は雨', 20180102);"
            , "INSERT INTO WordsOfToday (name, words, date) VALUES('Osamu', '今日は雨', 20180103);"
            , "INSERT INTO WordsOfToday (name, words, date) VALUES('Osamu', '今日はいい天気', 20180104);"
            , "INSERT INTO WordsOfToday (name, words, date) VALUES('Ikeda', '今日は曇り', 20180105);"
            , "INSERT INTO WordsOfToday (name, words, date) VALUES('Taiki', '今日はいい天気', 20180106);"
            , "INSERT INTO WordsOfToday (name, words, date) VALUES('Ikeda', '今日はいい天気', 20180107);"
        )
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.beginTransaction()
        try {
            executeQuery(db, CREATE_TABLE_QUERY)
            for (insertQuery in DEFAULT_DATA_LIST) {
                executeQuery(db, insertQuery)
            }
            db?.setTransactionSuccessful()
        } finally {
            db?.endTransaction()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    private fun executeQuery(db: SQLiteDatabase?, query: String) {
        db?.execSQL(query)
    }
}