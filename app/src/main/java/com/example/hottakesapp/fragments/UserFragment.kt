package com.example.hottakesapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hottakesapp.*

class UserFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        var id = sharedPref?.getString("id",null)

        if(id!=null) {
            var takeslist = mutableListOf<Take>()

            //these lines might break it
            val button = getView()?.findViewById(R.id.button) as Button
            val user = getView()?.findViewById(R.id.editTextTextPersonName2) as EditText
            val pass = getView()?.findViewById(R.id.editTextTextPersonName3) as EditText
            button.visibility=View.GONE
            pass.visibility =View.GONE
            user.visibility=View.GONE
            
            val recyclerView = getView()?.findViewById(R.id.recycler) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this.context)

            recyclerView.adapter = profileTakeAdapter(takeslist)
            if (id != null) {
                getTakesByUser(id) { myNote ->
                    runOnUiThread {
                        for (n in myNote) {
                            takeslist.add(n)
                        }
                        recyclerView.adapter = profileTakeAdapter(takeslist)
                    }
                }
            }
        }
        else{
            val button = getView()?.findViewById(R.id.button) as Button
            val user = getView()?.findViewById(R.id.editTextTextPersonName2) as EditText
            val pass = getView()?.findViewById(R.id.editTextTextPersonName3) as EditText
            var text:String
            button.setOnClickListener{
                postUser(user.text.toString(),pass.text.toString()){
                    text = it._id
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    val editor = sharedPref?.edit()
                    if (editor != null) {
                        editor.putString("id",text)
                        editor.apply()
                    }
                }
                val check = (sharedPref?.getString("id", null))
                if(!check.isNullOrBlank()) {
                    val text = "User added!" + (sharedPref?.getString("id", null)
                        ?: String) + "go add a take!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }else{
                    val text = "Something went wrong, try another username!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
            }
        }
//        val button = getView()?.findViewById(R.id.button) as Button
//        val user = getView()?.findViewById(R.id.editTextTextPersonName2) as EditText
//        val pass = getView()?.findViewById(R.id.editTextTextPersonName3) as EditText
//
//        button.visibility = View.GONE
//        user.visibility = View.GONE
//        pass.visibility = View.GONE
//
//        val text = "User added!"
//        val duration = Toast.LENGTH_SHORT
//
//        val toast = Toast.makeText(context, text, duration)
//        toast.show()
//        var takeslist = mutableListOf<Take>()
//
//        val recyclerView = getView()?.findViewById(R.id.recycler) as RecyclerView
//        recyclerView.visibility= View.VISIBLE
//        recyclerView.layoutManager = LinearLayoutManager(this.context)
//        Log.i("location","above recyclerview")
//        recyclerView.adapter = profileTakeAdapter(takeslist)
//        if (id != null) {
//            getTakesByUser(id) { myNote ->
//                runOnUiThread {
//                    for (n in myNote) {
//                        takeslist.add(n)
//                    }
//                    recyclerView.adapter = profileTakeAdapter(takeslist)
//                }
//            }
//        }
//        recyclerView.visibility= View.VISIBLE
//        Log.i("location",takeslist.toString())
    }
    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }
}