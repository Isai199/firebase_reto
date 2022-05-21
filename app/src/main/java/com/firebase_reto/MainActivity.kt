package com.firebase_reto

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.firebase_reto.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                binding = ActivityMainBinding.inflate(layoutInflater)
                val view = binding.root
                setContentView(view)





        // Write a message to the database
        val database = Firebase.database
        //val myRef = database.getReference("examinar")
        val myRef = database.reference
        /*myRef.child("movie").child("Title").setValue("Endgame")
        myRef.child("movie").child("Year").setValue("2019")
        myRef.child("movie").child("imdbID").setValue("tt4154796")
        myRef.child("movie").child("Type").setValue("movie")
        myRef.child("movie").child("Poster").setValue("https://m.media-amazon.com/images/M/MV5BNGZiMzBkZjMtNjE3Mi00MWNlLWIyYjItYTk3MjY0Yjg5ODZkXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX300.jpg")
        myRef.child("movie").child("Country").setValue("US")
        myRef.child("movie").child("Gender").setValue("Action")*/
                myRef.child("movie").get().addOnSuccessListener { response ->
                    Log.d("firebaseResponse",response.value.toString())


                    val resmap = response.value as ArrayList<Map<String, Any>>
                    var myarrayjson: Array<JSONObject> = arrayOf()

                    Log.d("firebaseResponse",resmap.toString())
                    resmap.forEach{ card ->

                        if(!card.isNullOrEmpty()){
                            Log.d("firebaseResponse", "por clave: ${card}")
                           val myjson = JSONObject(card)

                            myarrayjson += arrayOf(myjson)
                        }

                    }


                    //metodo 1

                   /*val jsonArray = JSONArray(response.value.toString())
                    var myarrayjson: Array<JSONObject> = arrayOf()

                    for (i in 1 until jsonArray.length()) {

                        Log.e("firebaseResponse",jsonArray.length().toString())



                        if(jsonArray[i].toString() != null){


                       val myjson = JSONObject(jsonArray[i].toString())

                        myarrayjson += arrayOf(myjson)
                        }


                    }*/

                    //Log.d("firebaseResponse",myjson.toString())

                    binding.notesRV.adapter = MovieAdapter(this,myarrayjson)

                }.addOnFailureListener{
                    Log.e("firebaseResponse", "Error getting data")
                }








                binding.idFAB.setOnClickListener {
                    // adding a click listener for fab button
                    // and opening a new intent to add a new note.
                    val intent = Intent(this@MainActivity, AddActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }


    }
}