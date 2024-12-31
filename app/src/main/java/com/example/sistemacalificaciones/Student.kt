package com.example.sistemacalificaciones

data class Student(
    val dni: String,
    var lastName: String,
    var firstName: String,
    var grade: Double
) {
    val qualification: String
        get() = when {
            grade < 5 -> "SS"
            grade < 7 -> "AP"
            grade < 9 -> "NT"
            else -> "SB"
        }
}
