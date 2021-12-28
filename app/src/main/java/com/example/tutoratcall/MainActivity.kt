package com.example.tutoratcall

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tutoratcall.database.DataBaseManager
import com.example.tutoratcall.database.EleveData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var exportButton: FloatingActionButton? = null
    private var layoutDisplay: LinearLayout? = null
    private var listCheckBox: TreeSet<CheckBoxV2>? = null
    private var dateDisplayText: TextView? = null
    private var cocherPresent: CheckBox? = null

    private val format by lazy { SimpleDateFormat("dd/MM/yyyy") }
    private var date: Date = Date()

    private lateinit var dataBaseManager: DataBaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main)
        dataBaseManager = DataBaseManager(this)

        initEleve();

        //findViewById
        exportButton = findViewById(R.id.button_export)
        layoutDisplay = findViewById(R.id.linear_layout_scroll_display_chip)
        dateDisplayText = findViewById(R.id.textView_date_cours)
        cocherPresent = findViewById(R.id.select_Mode_CheckBox)

        val str = getString(R.string.cours_du_textview) + "  " + format.format(date)
        dateDisplayText!!.text = str

        //Listener
        exportButton!!.setOnClickListener {exportButton()}

        listCheckBox = TreeSet();
        var checkBox: CheckBoxV2
        val sameDay = (LIST_ELEVE.last().date.equals(format.format(date)))

        for (e in LIST_ELEVE) {
            checkBox = CheckBoxV2(this, e)
            checkBox.setText()
            layoutDisplay!!.addView(checkBox)
            listCheckBox!!.add(checkBox)
            Log.d("PRINT", "${checkBox.text} $listCheckBox")
            if (sameDay) checkBox.isChecked = e.absent

        }

    }


    private fun initEleve() {
        LIST_ELEVE = dataBaseManager.getList()
    }

    private fun saveButton(showToast: Boolean = true) {
        save()
        if (showToast) Toast.makeText(this, getString(R.string.appel_save_toast), Toast.LENGTH_SHORT).show()
    }

    private fun exportButton() {
        saveButton(false)
        val text = StringBuilder()

        val presentBool = cocherPresent!!.isChecked
        val strTempo = if (presentBool) getString(R.string.present) else getString(R.string.absent)
        text.append(dateDisplayText!!.text).append(strTempo).append("\n\n"
        )
        var textOpt = StringBuilder()
        textOpt.append(getString(R.string.optional_student)).append(strTempo).append("\n\n")

        var nombreAbs = 0
        var nombreAbsOpt = 0

        var totalEleve = 0
        var totalEleveOpt = 0
        // parcours tout les élèves
        for (e in LIST_ELEVE) {
            if (e.optionnel) totalEleveOpt++
            else totalEleve++

            //Fait différence entre optionnel ou non
            if (e.absent ){
                if (e.optionnel) {
                    textOpt.append("   - ").append((e.name).uppercase()).append(" ").append(e.firstname).append("\n")
                    nombreAbsOpt++

                } else {
                    text.append("   - ").append((e.name).uppercase()).append(" ").append(e.firstname).append("\n")
                    nombreAbs++
                }
            }
        }


        text.append("\n")
            .append( if (presentBool) nombreAbs else (totalEleve - nombreAbs)).append(" / ")
            .append(totalEleve).append(" " + getString(R.string.eleve_present_note_text))
            .append("\n\n")
            .append(textOpt).append("\n")
            .append( if (presentBool) nombreAbsOpt else (totalEleveOpt - nombreAbsOpt)).append(" / ")
            .append(totalEleveOpt).append(" " + getString(R.string.eleve_present_note_text))


        val intent = Intent(this, ShowResultActivity::class.java)
        val bundle = Bundle()
        bundle.putString(INTENT_EXTRA_TEXT, text.toString())
        intent.putExtras(bundle)

        startActivity(intent)
    }


    private fun save() {
        for ( c in listCheckBox!!)  {
            c.eleve.upToDate()
            c.eleve.absent = c.isChecked
            dataBaseManager.update(c.eleve)
        }
    }

    override fun onPause() {
        super.onPause()
        save()
    }

}

var LIST_ELEVE = TreeSet<EleveData>();
const val INTENT_EXTRA_TEXT = "intentextrashowtext"


