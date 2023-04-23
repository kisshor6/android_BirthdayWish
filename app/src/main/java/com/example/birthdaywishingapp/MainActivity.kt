package com.example.birthdaywishingapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val birthdayName = findViewById<EditText>(R.id.name)
        val birthdayDate = findViewById<EditText>(R.id.date)
        val submit = findViewById<Button>(R.id.submitButton)

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val cal = Calendar.getInstance()
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                birthdayDate.setText(dateFormat.format(cal.time))
        }
        birthdayDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                dateSetListener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        submit.setOnClickListener {
            val name = birthdayName.text.toString()
            val dateOfBirth = birthdayDate.text.toString()
            if (name != "" && dateOfBirth != ""){
                val intent = Intent(this, birthdatScreen::class.java)
                intent.putExtra("name", name)
                intent.putExtra("date", dateOfBirth)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Please enter your name & date", Toast.LENGTH_LONG).show()
            }

        }
    }
}