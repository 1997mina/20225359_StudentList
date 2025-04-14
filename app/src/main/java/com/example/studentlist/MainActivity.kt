package com.example.studentlist

import StudentAdapter
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var mssv: EditText
    private lateinit var btnAdd: Button
    private lateinit var rvStudents: RecyclerView
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

        name = findViewById(R.id.etName)
        mssv = findViewById(R.id.etMSSV)
        btnAdd = findViewById(R.id.btnAdd)
        rvStudents = findViewById(R.id.rvStudents)

        rvStudents.layoutManager = LinearLayoutManager(this)
        adapter = StudentAdapter(studentList) { position ->
            studentList.removeAt(position)
            adapter.notifyItemRemoved(position)
            adapter.notifyItemRangeChanged(position, studentList.size - position)
        }
        rvStudents.adapter = adapter

        btnAdd.setOnClickListener {
            addStudent()
        }
    }

    private fun addStudent() {
        val studentName = name.text.toString()
        val studentId = mssv.text.toString()

        if (studentName.isEmpty() || studentId.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        studentList.add(0, Student(studentName, studentId))
        adapter.notifyItemInserted(0)
        rvStudents.scrollToPosition(0)

        name.text.clear()
        mssv.text.clear()
    }

    data class Student(val name: String, val mssv: String)
}