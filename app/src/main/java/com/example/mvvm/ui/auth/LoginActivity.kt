package com.example.mvvm.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityLoginBinding
import com.example.mvvm.ui.util.toast

 class LoginActivity : AppCompatActivity(),authListener {
   
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//name is according to our xml file
        val binding : ActivityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login)
//view Model
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
    }
    override fun onStarted() {
        toast("login started")
        //create a progress bar in xml file
        progress_bar.show()
    }
    override fun onSuccess(loginResponse: LiveData<String>) {
        toast("login Success")
        loginResponse.observe(
            this, Observer {
                progress_bar.hide()
                toast(it)
            }
        )
    }
    override fun onFaliure(message: String) {
        progress_bar.hide()
        toast(message)
    }
}