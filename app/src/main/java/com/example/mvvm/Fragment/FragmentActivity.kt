package com.example.mvvm.Fragment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.R

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)


       val buttonA: Button = findViewById(R.id.buttnFrg1)
        val buttonB:Button = findViewById(R.id.buttnFrg2)
        val buttonC : Button = findViewById(R.id.buttnFrg3 )
    }
}