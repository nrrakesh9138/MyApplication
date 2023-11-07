package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val greetingTextView = findViewById<TextView>(R.id.tvHello)
        val inputField = findViewById<EditText>(R.id.etName)
        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val offersButton = findViewById<Button>(R.id.btnOffers)
        val youtubeButton = findViewById<Button>(R.id.btnYoutube)
        var enteredName = ""
        submitButton.setOnClickListener {
            enteredName = inputField.text.toString()
            if (enteredName == "") {
                offersButton.visibility = INVISIBLE
                greetingTextView.text = ""
                Toast.makeText(
                    this@MainActivity,
                    "Please, enter your name!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                val message = "Welcome $enteredName"
                greetingTextView.text = message
                inputField.text.clear()
                offersButton.visibility = VISIBLE
            }
        }
        offersButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("user", enteredName)
            startActivity(intent)
        }
        youtubeButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.data = Uri.parse("https://www.youtube.com/watch?v=BCSlZIUj18Y&t=9252s&ab_channel=AppDevNotes");
            intent.setPackage("com.google.android.youtube")
            try{
                startActivity(intent);
            } catch(e:ActivityNotFoundException) {
                Toast.makeText(this, "There is no package available in android", Toast.LENGTH_LONG).show();
            }
//            val location = Uri.parse("geo:0,0?q=Tvam+Technologies+Private,+Limited+Bengaluru,+Karnataka")
//            val mapIntent = Intent(Intent.ACTION_VIEW, location)
//
//            // Try to invoke the intent.
//            try {
//                startActivity(mapIntent)
//            } catch (e: ActivityNotFoundException) {
//                // Define what your app should do if no activity can handle the intent.
//            }

        }
    }
}