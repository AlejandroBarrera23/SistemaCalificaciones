package com.example.sistemacalificaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class StudentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val filter = intent.getStringExtra("filter")
        val students = when (filter) {
            "failures" -> StudentManager.getFailingStudents()
            "successes" -> StudentManager.getPassingStudents()
            "mh" -> StudentManager.getMHStudents()
            else -> StudentManager.getStudents()
        }

        val lvStudents = findViewById<ListView>(R.id.lv_students)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            students.map { student ->
                "${student.dni} ${student.lastName}, ${student.firstName} ${student.grade} ${student.qualification}"
            }
        )

        lvStudents.adapter = adapter

        lvStudents.setOnItemClickListener { _, _, position, _ ->
            val selectedStudent = students[position]
            val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                putExtra("dni", selectedStudent.dni)
            }
            startActivity(intent)
        }
    }

    fun returnToMainMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}


