package com.example.mvvm.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mvvm.R

class BFragment : Fragment() {
    /
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       val view =  inflater.inflate(R.layout.fragment_b, container, false)
           val textView: TextView = view.findViewById(R.id.textView1)
        return  view
    }

}