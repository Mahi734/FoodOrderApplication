package com.example.mvvm.ui.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

//extension function for context

fun Context.toast(message:String)
{
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}
fun ProgressBar.show(){
    visibility = View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}