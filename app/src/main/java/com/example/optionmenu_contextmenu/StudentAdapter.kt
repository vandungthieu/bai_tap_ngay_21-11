package com.example.optionmenu_contextmenu
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentAdapter(
    private val context: Activity,
    private val layoutId: Int,
    private val myList: ArrayList<StudentModel>
): ArrayAdapter<StudentModel>(context, layoutId, myList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layoutId, parent, false)

        val student = myList[position]

        val nameView = view.findViewById<TextView>(R.id.text_student_name)
        val mssvView = view.findViewById<TextView>(R.id.text_student_id)

        nameView.text = student.studentName
        mssvView.text = student.studentId

        return view
    }
}