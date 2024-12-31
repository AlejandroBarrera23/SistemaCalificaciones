package com.example.sistemacalificaciones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_add_student).setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }

        findViewById<Button>(R.id.btn_view_students).setOnClickListener {
            val intent = Intent(this, StudentListActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_show_failures).setOnClickListener {
            val failures = StudentManager.getFailingStudents()
            if (failures.isEmpty()) {
                Toast.makeText(this, "No hay alumnos suspensos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, StudentListActivity::class.java).apply {
                    putExtra("filter", "failures")
                }
                startActivity(intent)
            }
        }

        findViewById<Button>(R.id.btn_show_successes).setOnClickListener {
            val successes = StudentManager.getPassingStudents()
            if (successes.isEmpty()) {
                Toast.makeText(this, "No hay alumnos aprobados", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, StudentListActivity::class.java).apply {
                    putExtra("filter", "successes")
                }
                startActivity(intent)
            }
        }

        findViewById<Button>(R.id.btn_show_candidates).setOnClickListener {
            val mhStudents = StudentManager.getMHStudents()
            if (mhStudents.isEmpty()) {
                Toast.makeText(this, "No hay candidatos a matrícula de honor", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, StudentListActivity::class.java).apply {
                    putExtra("filter", "mh")
                }
                startActivity(intent)
            }
        }
    }
}

