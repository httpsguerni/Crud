package br.com.httpsguerni.crud

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context) : SQLiteOpenHelper(context, "database.db", null, 1) {

    val tableDB = "livros"
    val id = "_id"
    val titulo = "titulo"
    val autor = "autor"
    val editora = "editora"
    val versao = 1

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE ${tableDB} (" +
                "$id INTEGER primary key autoincrement," +
                "$titulo text," +
                "$autor text," +
                "$editora, text)"
        db!!.execSQL(sql)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
       db!!.execSQL("DROP TABLE IF EXISTS $tableDB")
        onCreate(db)
    }
}