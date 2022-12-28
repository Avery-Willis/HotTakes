package com.example.hottakesapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userid = "63a663710ca1ef4a9ce6eb14"

        var takeslist = mutableListOf<Take>()

        val recyclerView = getView()?.findViewById(R.id.recycler) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        recyclerView.adapter = profileTakeAdapter(takeslist)
        getTakesByUser(userid) { myNote ->
            runOnUiThread {
                for(n in myNote){
                    Log.i("getTakes","added a take")
                    takeslist.add(n)

                }
                recyclerView.adapter = profileTakeAdapter(takeslist)

            }
        }
    }
    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }
}