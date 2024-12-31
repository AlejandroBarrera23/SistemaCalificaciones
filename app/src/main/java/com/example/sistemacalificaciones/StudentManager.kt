package com.example.sistemacalificaciones

object StudentManager {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student): Boolean {
        if (students.any { it.dni == student.dni }) return false
        students.add(student)
        return true
    }

    fun deleteStudent(dni: String): Boolean {
        return students.removeIf { it.dni == dni }
    }

    fun findStudent(dni: String): Student? {
        return students.find { it.dni == dni }
    }

    fun updateGrade(dni: String, newGrade: Double): Boolean {
        val student = findStudent(dni)
        student?.grade = newGrade
        return student != null
    }

    fun getStudents(): List<Student> = students

    fun getFailingStudents(): List<Student> = students.filter { it.grade < 5 }

    fun getPassingStudents(): List<Student> = students.filter { it.grade >= 5 }

    fun getMHStudents(): List<Student> = students.filter { it.grade == 10.0 }
}
