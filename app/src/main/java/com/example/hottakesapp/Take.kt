package com.example.hottakesapp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Take (
    val _id: String,
    val text: String,
    val upvotes: Int,
    val downvotes: Int,
    val userid:String,
    val __v:Any
    )

@JsonClass(generateAdapter = true)
data class TakeList (
    val listOfTakes : List<Take>
)

@JsonClass(generateAdapter = true)
data class TakeOut(
    val text:String
)