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
        dao.create(EleveData(name = "gouerec", firstname = "Jules"))
        dao.create(EleveData(name = "belgeri", firstname = "Loris"))
        dao.create(EleveData(name = "dupont", firstname = "Louis"))
        dao.create(EleveData(name = "duchateau", firstname = "Camille"))
        dao.create(EleveData(name = "ladiray", firstname = "Hugo"))
        dao.create(EleveData(name = "gassmann", firstname = "Victor"))
        dao.create(EleveData(name = "wittmann", firstname = "Gregory"))
        dao.create(EleveData(name = "hayaf", firstname = "Adame"))
        dao.create(EleveData(name = "martin", firstname = "Thiebaud"))
        dao.create(EleveData(name = "robin", firstname = "Kylian"))
        dao.create(EleveData(name = "renaux", firstname = "Anna"))
        dao.create(EleveData(name = "guyenet", firstname = "Victor"))
        dao.create(EleveData(name = "carvalho", firstname = "Mathis"))
        dao.create(EleveData(name = "han", firstname = "Zhihang"))
        dao.create(EleveData(name = "crugnola", firstname = "Loris"))
        dao.create(EleveData(name = "delikaya", firstname = "Yusuf"))
        dao.create(EleveData(name = "gavin", firstname = "Jeremie"))
        dao.create(EleveData(name = "fischer", firstname = "Elise"))
        dao.create(EleveData(name = "sabir", firstname = "Yassin"))
        dao.create(EleveData(name = "idioux", firstname = "Thomas"))
        dao.create(EleveData(name = "dizmbe olengue", firstname = "Jason"))
        dao.create(EleveData(name = "mavaetau", firstname = "Malia Anna"))


        dao.create(EleveData(name = "claus", firstname = "Ugo", optionnel = true))
        dao.create(EleveData(name = "sow", firstname = "Amadou", optionnel = true))
        dao.create(EleveData(name = "friouichen", firstname = "Mohammed", optionnel = true))
        dao.create(EleveData(name = "edouard", firstname = "Lucas", optionnel = true))
        dao.create(EleveData(name = "hugerot", firstname = "Quentin", optionnel = true))
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