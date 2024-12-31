package com.example.sistemacalificaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val dni = intent.getStringExtra("dni")
        val student = dni?.let { StudentManager.findStudent(it) }

        val tvStudentInfo = findViewById<TextView>(R.id.tv_student_info)
        val etUpdateGrade = findViewById<EditText>(R.id.et_update_grade)
        val btnUpdateGrade = findViewById<Button>(R.id.btn_update_grade)
        val btnDeleteStudent = findViewById<Button>(R.id.btn_delete_student)

        if (student == null) {
            Toast.makeText(this, "Alumno no encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        tvStudentInfo.text = "${student.dni} ${student.lastName}, ${student.firstName}\nNota: ${student.grade}\nCalificación: ${student.qualification}"

        btnUpdateGrade.setOnClickListener {
            val newGrade = etUpdateGrade.text.toString().toDoubleOrNull()
            if (newGrade == null || newGrade !in 0.0..10.0) {
                Toast.makeText(this, "Introduce una nota válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            StudentManager.updateGrade(student.dni, newGrade)
            tvStudentInfo.text = "${student.dni} ${student.lastName}, ${student.firstName}\nNota: ${student.grade}\nCalificación: ${student.qualification}"
            Toast.makeText(this, "Nota actualizada", Toast.LENGTH_SHORT).show()
        }

        btnDeleteStudent.setOnClickListener {
            if (StudentManager.deleteStudent(student.dni)) {
                Toast.makeText(this, "Alumno eliminado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun returnToMainMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}

