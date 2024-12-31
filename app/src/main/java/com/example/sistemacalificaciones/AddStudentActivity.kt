package com.example.sistemacalificaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val etDni = findViewById<EditText>(R.id.et_dni)
        val etLastName = findViewById<EditText>(R.id.et_lastname)
        val etFirstName = findViewById<EditText>(R.id.et_firstname)
        val etGrade = findViewById<EditText>(R.id.et_grade)
        val btnSave = findViewById<Button>(R.id.btn_save_student)

        btnSave.setOnClickListener {
            val dni = etDni.text.toString()
            val lastName = etLastName.text.toString()
            val firstName = etFirstName.text.toString()
            val grade = etGrade.text.toString().toDoubleOrNull()

            if (dni.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || grade == null || grade !in 0.0..10.0) {
                Toast.makeText(this, "Por favor, introduce datos válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val student = Student(dni, lastName, firstName, grade)
            if (StudentManager.addStudent(student)) {
                Toast.makeText(this, "Alumno añadido correctamente", Toast.LENGTH_SHORT).show()
                finish() // Vuelve a la actividad anterior
            } else {
                Toast.makeText(this, "El DNI ya existe", Toast.LENGTH_SHORT).show()
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

