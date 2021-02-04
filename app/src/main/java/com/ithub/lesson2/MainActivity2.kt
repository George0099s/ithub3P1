package com.ithub.lesson2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var edFinish: EditText
    private lateinit var finish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main2_activity)
        val thisIntent = intent // intent == getIntent/setIntent
        textView = findViewById(R.id.text_view)
        edFinish = findViewById(R.id.res_ed)
        finish = findViewById(R.id.finish)
        val name = thisIntent.getStringExtra("name")
        val age = thisIntent.getIntExtra("age", 0)

        textView.text =
            "Имя = ${thisIntent.getStringExtra("name")} \nВозраст = $age" // text = "smthg" equals setText("smthg")

        finish.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("result", if (edFinish.text.toString().isNotEmpty()) edFinish.text.toString() else "Не данных")
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}