package com.example.hottakesapp.fragments

import android.content.Context
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

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val userid = sharedPref?.getString("id",null)

        val textin: EditText = getView()?.findViewById(R.id.editTextTextPersonName2) ?: EditText(context)

        val button = getView()?.findViewById(R.id.button)?: Button(context)
        button.setOnClickListener{
            if (userid != null) {
                    if (textin.text.isNotEmpty()) {
                        post(userid, textin.text.toString())
                        val text = "Take was added!"
                        val duration = Toast.LENGTH_SHORT

                        val toast = Toast.makeText(context, text, duration)
                        toast.show()
                    }
            }
            else{
                val text = "Sign In Before Adding A Take!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }

    }
}