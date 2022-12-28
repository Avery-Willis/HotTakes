package com.example.hottakesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hottakesapp.R
import com.example.hottakesapp.post

class AddFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //TODO need to delete and add id from sharedpreferences
        //TODO add logic for sign in/up before adding take
        val userid = "63a663710ca1ef4a9ce6eb14"

        val textin: EditText = getView()?.findViewById(R.id.editTextTextPersonName2) ?: EditText(context)

        val button = getView()?.findViewById(R.id.button)?: Button(context)
        button.setOnClickListener{
            post(userid,textin.text.toString())
            //TODO add better notification here!!
            //TODO (maybe) return to home screen
            val text = "Take was added!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

    }
}