package com.firebase_reto

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase_reto.databinding.ActivitySaveBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*

class AddActivity: AppCompatActivity() {
    private lateinit var binding: ActivitySaveBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)





        binding.idBtn.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.
            val noteTitle = binding.idEdtTitle.text.toString()
            val noteYear =binding.idEdtYear.text.toString()
            val noteType=binding.idEdtType.text.toString()
            val noteCountry =binding.idEdtCountry.text.toString()
            val noteGender =binding.idEdtGender.text.toString()
            val noteLink =binding.idEdtNoteLink.text.toString()
            // on below line we are checking the type
            // and then saving or updating the data.

                if (noteTitle.isNotEmpty() && noteYear.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())


                    val database = Firebase.database
                    //val myRef = database.getReference("examinar")
                    val myRef = database.reference
                    val alphabet = 'a'..'z'


                    myRef.child("movie").get().addOnSuccessListener { response ->

                        val jsonArray = JSONArray(response.value.toString())
                        val idNumber = jsonArray.length()
                        myRef.child("movie").child("$idNumber"/*"id"+"$randomLetter"+"$randomLetterTwo"+"$randomLetterThree"+"$randomLetterFort"+"$noteYear"*/).setValue(Movie("$noteTitle","$noteYear","$noteType","$noteCountry","$noteGender","$noteLink"))


                    }


                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }

            // opening the new activity on below line
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }




    }
}