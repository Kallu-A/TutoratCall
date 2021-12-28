package com.example.tutoratcall.database

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.text.SimpleDateFormat
import java.util.*

@DatabaseTable( tableName = "Table_Eleve")
class EleveData(

    @DatabaseField
    var absent: Boolean = false ,

    @DatabaseField
    var name: String = "undefinded",

    @DatabaseField
    var firstname: String = "undefinded",

    @DatabaseField
    var optionnel: Boolean = false

): Comparable<EleveData> {
    @DatabaseField (generatedId = true)
    var id: Int = 0

    @DatabaseField
    var date = SimpleDateFormat("dd/MM/yyyy").format(Date())

    override fun compareTo(other: EleveData): Int {
        return if (this.optionnel) {
            if (other.optionnel) returnCompareSameOpt(other)
            else 1
        }  else {
            if (other.optionnel) -1
            else returnCompareSameOpt(other)
        }
    }

    private fun returnCompareSameOpt(other: EleveData): Int {
        if (this.name.compareTo(other.name) != 0)
            return this.name.compareTo(other.name)
        else return this.firstname.compareTo(other.firstname)
    }

    fun upToDate() {
        date = SimpleDateFormat("dd/MM/yyyy").format(Date())
    }

}