package com.example.loginapp


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName: EditText = findViewById(R.id.userName)
        val password: EditText = findViewById(R.id.password)

        val button: Button = findViewById(R.id.submitButton)
        //
        button.setOnClickListener {
          //  val intent = Intent(this,SecondActivity::class.java)
          //  val userName = userName.text.toString()
           // val password = password.text.toString()
            intent.putExtra("Name","userName")
            intent.putExtra("Password","password")
            startActivity(intent)
            Log.d("MainActivity","$userName$password")
       }
        fetchUser()
    }

    private fun fetchUser() {
        val callApi = ApiService.apiService
        val userName: EditText = findViewById(R.id.userName)
        val password: EditText = findViewById(R.id.password)
        val button: Button = findViewById(R.id.submitButton)
        val data = MappingData(
            email = userName.text.toString(),
            password = password.text.toString()
        )
        callApi.postData(data).enqueue(object : Callback<MappingData>
        {
            override fun onResponse(call: Call<MappingData>, response: Response<MappingData>) {
                if (response.isSuccessful) {
                    // Successful response
                    val user = response.body()
                    Log.e("MainActivity","$user")
                }
                else {
                    // Error response (non-2xx)
                    Log.e("MainActivity", "Error: ${response.errorBody()}")
                }
            }
            override fun onFailure(call: Call<MappingData>, t: Throwable) {
                // Network failure (e.g., no internet)
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }
}
