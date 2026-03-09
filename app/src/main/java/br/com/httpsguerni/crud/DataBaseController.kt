package br.com.httpsguerni.crud

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DataBaseController(context: Context) {
    private lateinit var sqldb: SQLiteDatabase
    private var db: DataBase = DataBase(context)


    fun insertData(title: String, author: String, publisher: String): String {
        val values = ContentValues()

        sqldb = db.writableDatabase
        values.put(db.titulo, title)
        values.put(db.autor, author)
        values.put(db.editora, publisher)

        val resultado: Long = sqldb.insert(db.tableDB, null, values)

        sqldb.close()

        return if (resultado == -1L) {
            Log.e(TAG, "Erro ao inserir registro")
            "Erro ao inserir registro"
        } else {
            Log.i(TAG, "Registro inserido com sucesso")
            "Registro inserido com sucesso"
        }
    }

    fun selectData(): Cursor {
        val campos = arrayOf(db.id, db.titulo, db.autor, db.editora)
        sqldb = db.readableDatabase

        val cursor = sqldb.query(
            db.tableDB,
            campos,
            null,
            null,
            null,
            null,
            null
        )

        return cursor
    }

    fun updateData(id: Int, title: String, autor: String, editora: String) {
        var valores = ContentValues()


        sqldb = db.writableDatabase

        val where = "${db.id} = $id"


        if (!title.isNullOrBlank()) {
            valores.put(db.titulo, title)
        }

        if (!autor.isNullOrBlank()) {
            valores.put(db.autor, autor)
        }

        if (!editora.isNullOrBlank()) {
            valores.put(db.editora, editora)
        }


        sqldb.update(db.tableDB, valores, where, null)
        sqldb.close()
    }

    fun deleteData(id: Int) {
        val where = "${db.id} = $id"
        sqldb = db.readableDatabase
        sqldb.delete(db.tableDB, where, null)
        sqldb.close()
    }

}