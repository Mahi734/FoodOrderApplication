package com.example.mvvm.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MyFragment : Fragment() {

    // This method inflates the fragment's layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment's layout file (res/layout/fragment_my.xml)
        return inflater.inflate(R.layout.fragment_my, container, false)
    }
}
