package com.example.hottakesapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TakeAdapter (
    private val takelist : List<Take>,
) : RecyclerView.Adapter<TakeAdapter.TakeViewHolder>(){

    class TakeViewHolder(view : View): RecyclerView.ViewHolder(view){
        val titleText : TextView = view.findViewById(R.id.titleYY)
        val up : ImageButton = view.findViewById(R.id.imageButton)
        val down : ImageButton = view.findViewById(R.id.imageButton2)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TakeAdapter.TakeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.take, parent, false)
        return TakeViewHolder(view)//, mAdapterOnClickHandler)
    }

    override fun onBindViewHolder(holder: TakeViewHolder, position: Int) {
        val take : Take = takelist[position]

        val id :String = take._id

        holder.titleText.text = take.text

        holder.up.setOnClickListener {
                upvote(id)
                holder.up.setBackgroundColor(Color.argb(50, 255, 10, 10))
                holder.up.isEnabled = false
                holder.down.isEnabled = false
        }
        holder.down.setOnClickListener {
                downvote(id)
                holder.down.setBackgroundColor(Color.argb(50, 0, 0, 255))
                holder.down.isEnabled = false
                holder.up.isEnabled = false
        }
    }

    override fun getItemCount(): Int {
        return takelist.size
    }
}