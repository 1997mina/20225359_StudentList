package com.example.studentlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var Name: EditText
    private lateinit var MSSV: EditText
    private lateinit var btnAdd: Button
    private lateinit var lvStudents: ListView
    private var studentList = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Name = findViewById(R.id.etName)
        MSSV = findViewById(R.id.etMSSV)
        btnAdd = findViewById(R.id.btnAdd)
        lvStudents = findViewById(R.id.lvStudents)

        studentList = mutableListOf()
        adapter = StudentAdapter(this, studentList)
        lvStudents.adapter = adapter

        // Xử lý sự kiện thêm sinh viên
        btnAdd.setOnClickListener {
            addStudent()
        }
    }

    private fun addStudent() {
        val name = Name.text.toString()
        val mssv = MSSV.text.toString()

        if (name.isEmpty() || mssv.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        studentList.add(0, Student(name, mssv))
        adapter.notifyDataSetChanged()

        Name.text.clear()
        MSSV.text.clear()
    }

    inner class StudentAdapter(
        context: android.content.Context,
        private val students: MutableList<Student>
    ) : ArrayAdapter<Student>(context, 0, students) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)

            val student = students[position]
            view.findViewById<TextView>(R.id.tvName).text = student.name
            view.findViewById<TextView>(R.id.tvMSSV).text = student.mssv

            view.findViewById<Button>(R.id.btnDelete).setOnClickListener {
                students.removeAt(position)
                notifyDataSetChanged()
            }

            return view
        }
    }

    data class Student(val name: String, val mssv: String)
}