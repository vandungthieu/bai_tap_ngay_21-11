package com.example.optionmenu_contextmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.EditText
import android.widget.Button

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // Xử lý thêm sinh viên khi bấm nút "Lưu"
        findViewById<Button>(R.id.button_ok).setOnClickListener {
            val name = findViewById<EditText>(R.id.add_hoten).text.toString()
            val mssv = findViewById<EditText>(R.id.add_mssv).text.toString()

            if (name.isNotEmpty() && mssv.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("name", name)
                resultIntent.putExtra("mssv", mssv)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        findViewById<Button>(R.id.button_cancel).setOnClickListener{
            finish()
        }
    }
}
