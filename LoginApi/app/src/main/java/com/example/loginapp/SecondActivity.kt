package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.util.jar.Attributes.Name

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val Name = intent.getStringExtra("Name")
        val Password  = intent.getStringExtra("Password")

        val Both:String = Name + Password

        Toast.makeText(this,Both,Toast.LENGTH_LONG).show()
    }
}