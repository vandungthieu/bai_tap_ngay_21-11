package com.example.optionmenu_contextmenu

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val name = intent.getStringExtra("name")
        val mssv = intent.getStringExtra("mssv")
        val position = intent.getIntExtra("position", -1)

        // Đảm bảo bạn dùng đúng ID cho các EditText
        findViewById<EditText>(R.id.edit_hoten).setText(name)
        findViewById<EditText>(R.id.edit_mssv).setText(mssv)

        findViewById<Button>(R.id.button_ok_edit).setOnClickListener {
            val updatedName = findViewById<EditText>(R.id.edit_hoten).text.toString()
            val updatedMssv = findViewById<EditText>(R.id.edit_mssv).text.toString()

            if (updatedName.isNotEmpty() && updatedMssv.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("name", updatedName)
                resultIntent.putExtra("mssv", updatedMssv)
                resultIntent.putExtra("position", intent.getIntExtra("position", -1))  // Trả lại vị trí
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        findViewById<Button>(R.id.button_cancel_edit).setOnClickListener{
            finish()

        }
    }
}

