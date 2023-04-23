package com.example.birthdaywishingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class birthdatScreen : AppCompatActivity() {
    lateinit var textViewMsg : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthdat_screen)

        textViewMsg = findViewById(R.id.textViewDate)

        val name = intent.getStringExtra("name")
        val dateOfBirth = intent.getStringExtra("date")

        val age = getAgeFromDOB(dateOfBirth)
        wishHappyBirthday(name.toString(), age)
    }

    private fun getAgeFromDOB(dateOfBirth: String?): Int {
        val dobDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateOfBirth)
        val calDOB = Calendar.getInstance()
        val calToday = Calendar.getInstance()

        calDOB.time = dobDate
        val yearDOB = calDOB.get(Calendar.YEAR)
        val monthDOB = calDOB.get(Calendar.MONTH)
        val dayDOB = calDOB.get(Calendar.DAY_OF_MONTH)

        val yearToday = calToday.get(Calendar.YEAR)
        val monthToday = calToday.get(Calendar.MONTH)
        val dayToday = calToday.get(Calendar.DAY_OF_MONTH)

        var age = yearToday - yearDOB

        if (monthToday < monthDOB || (monthToday == monthDOB && dayToday < dayDOB)) {
            age--
        }
        if (age < 1){
            Toast.makeText(this, "Invalid Age", Toast.LENGTH_SHORT).show()
            finish()
        }

        return age
    }
    fun wishHappyBirthday(name: String, age: Int) {
        val message = " Wish You Happy ${age}th Birthday, ${name}! \n Wish you all the Best for your upComing" +
                " Future... Stay Health, wealthy and happy see you soon"
        textViewMsg.text = message
    }
}