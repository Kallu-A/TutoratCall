package com.example.tutoratcall

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout

class ShowResultActivity : AppCompatActivity() {

    private var textShow: TextView? = null
    private var returnBackButton: ImageButton? = null
    private var copyButton: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_result)

        textShow = findViewById(R.id.text_show_edittext)
        returnBackButton = findViewById(R.id.return_imagebutton)
        copyButton = findViewById(R.id.copy_buttonfloatting)

        val bundle = intent.extras

        val text = bundle!!.get(INTENT_EXTRA_TEXT)


        Log.d("PRINT", " , $textShow , $text")
        textShow!!.text = text.toString()

        returnBackButton!!.setOnClickListener {
            this.finish()
        }



        copyButton!!.setOnClickListener {
            val clip: ClipData = ClipData.newPlainText(getString(R.string.appelLabel), text.toString())
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, getString(R.string.copy), Toast.LENGTH_SHORT).show()

        }

    }






}