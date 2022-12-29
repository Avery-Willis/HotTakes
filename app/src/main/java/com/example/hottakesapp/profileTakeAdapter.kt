package com.example.hottakesapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class profileTakeAdapter (
    private val takelist : List<Take>,
) : RecyclerView.Adapter<profileTakeAdapter.profileTakeViewHolder>(){

    class profileTakeViewHolder(view : View): RecyclerView.ViewHolder(view){
        val titleText : TextView = view.findViewById(R.id.titleYY)
        val progress : ProgressBar = view.findViewById(R.id.progressBar2)
        val progressEnd:ProgressBar = view.findViewById(R.id.progressBar3)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): profileTakeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profiletake, parent, false)
        return profileTakeViewHolder(view)
    }

    override fun onBindViewHolder(holder: profileTakeViewHolder, position: Int) {
        val take : Take = takelist[position]

        val id :String = take._id

        val upvotes = take.upvotes
        val downvotes = take.downvotes
        //changing
        holder.titleText.text =take.text

        holder.progress.progress = (100*upvotes/(upvotes+downvotes))
        holder.progressEnd.progress = (100-100*upvotes/(upvotes+downvotes))


    }

    override fun getItemCount(): Int {
        return takelist.size
    }
}