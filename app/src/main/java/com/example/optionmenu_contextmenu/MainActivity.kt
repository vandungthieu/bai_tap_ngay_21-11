package com.example.optionmenu_contextmenu

import android.os.Bundle
import android.content.Intent
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.AdapterView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var lv: ListView

    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    private lateinit var studentAdapter: StudentAdapter

    private lateinit var addStudentLauncher: ActivityResultLauncher<Intent>
    private lateinit var editStudentLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv = findViewById(R.id.list_view_students)

        studentAdapter = StudentAdapter(this, R.layout.layout_student_item, ArrayList(students))
        lv.adapter = studentAdapter

        registerForContextMenu(lv)

        // Register the launcher for adding students
        addStudentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val name = data?.getStringExtra("name")
                val mssv = data?.getStringExtra("mssv")

                if (name != null && mssv != null) {
                    students.add(StudentModel(name, mssv))
                    studentAdapter.notifyDataSetChanged()
                }
            }
        }

        // Register the launcher for editing students
        editStudentLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val updatedName = data?.getStringExtra("name")
                val updatedMssv = data?.getStringExtra("mssv")
                val position = data?.getIntExtra("position", -1)

                if (updatedName != null && updatedMssv != null && position != null && position >= 0) {
                    val student = students[position]
                    student.studentName = updatedName
                    student.studentId = updatedMssv
                    studentAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_new -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                addStudentLauncher.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        return when (item.itemId) {
            R.id.action_edit -> {
                val student = students[position]
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("name", student.studentName)
                intent.putExtra("mssv", student.studentId)
                intent.putExtra("position", position)
                editStudentLauncher.launch(intent)
                true
            }

            R.id.action_remove -> {
                students.removeAt(position)
                studentAdapter.notifyDataSetChanged()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

}