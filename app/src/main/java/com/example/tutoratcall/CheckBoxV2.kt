package com.example.tutoratcall

import android.content.Context
import android.content.res.ColorStateList
import com.example.tutoratcall.database.EleveData

class CheckBoxV2(context: Context?, val eleve: EleveData) : androidx.appcompat.widget.AppCompatCheckBox(context!!), Comparable<CheckBoxV2> {


    override fun compareTo(other: CheckBoxV2): Int {
        if ( (eleve.name.compareTo(other.eleve.name)  ) != 0 ) return (eleve.name.compareTo(other.eleve.name)  )
        else return (eleve.firstname.compareTo(other.eleve.firstname))

    }

    override fun equals(other: Any?): Boolean {
        if (other !is CheckBoxV2) return false
        return (eleve.name == other.eleve.name && eleve.firstname == other.eleve.firstname)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    fun setText() {
        val str = "${eleve.name.uppercase()} , ${eleve.firstname} "
        text = str
        if (eleve.optionnel) setTextColor(resources.getColor(R.color.colorOptionnelText))
        else setTextColor(resources.getColor(R.color.colorOnPrimary))
    }

}