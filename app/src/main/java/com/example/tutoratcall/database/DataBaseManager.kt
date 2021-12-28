package com.example.tutoratcall.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import java.util.*


class DataBaseManager(context: Context): OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val dao: Dao<EleveData, Int> = getDao(EleveData::class.java)

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        try {
            TableUtils.createTable(connectionSource, EleveData::class.java)
            Log.i("PRINT", "database bien créer")
            init()
        } catch (e: Exception) {
            Log.i("PRINT", "erreur création database")
        }
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val list = getList()
        for ( e in list) dao.delete(e)
        init()
    }
    
    private fun init() {
        //Modify here !!
        dao.create(EleveData(name = "name1", firstname = "firstname1"))
        dao.create(EleveData(name = "name2", firstname = "firstname2"))
        dao.create(EleveData(name = "name3", firstname = "firstname3"))
        dao.create(EleveData(name = "name4", firstname = "firstname4", optionnel = true))
        dao.create(EleveData(name = "name5", firstname = "firstname5"))

    }

    fun getList(): TreeSet<EleveData> {
        val tree = TreeSet<EleveData>()
        val list = dao.queryForAll()

        for ( e in list) tree.add(e)
        return tree
    }

    fun update(eleve: EleveData) {
        dao.update(eleve)
    }
}

const val DATABASE_NAME = "Database Eleve Tutorat"
const val DATABASE_VERSION = 3