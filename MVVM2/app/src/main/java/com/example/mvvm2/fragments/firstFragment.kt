package com.example.mvvm2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mvvm2.FirstFragment
import com.example.mvvm2.R

class firstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =  inflater.inflate(R.layout.fragment_first2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("KEY")?:"default"
        textView.text=name

        button.setOnClickListener{
            Toast.makeText(requireContext(),"this is fragment class",Toast.LENGTH_LONG).show()
        }
    }
    //dynamic
    supportFragmentManager.beginTransaction().add(R.id.container.FirstFragment()).commitNow()
//set data to fragment using bundle method
    val bundle = Bundle()
    bundle.putString("KEY","Mahesh")
    val fragment = FirstFragment()
    fragment.arguments = bundle


}