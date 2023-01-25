package com.example.hottakesapp

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


fun getTakes(callback : (List<Take>) -> Unit){
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://104.197.168.4/takes")
        .get()
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                //val jsonObject = JSONObject(body).toString()
                print(body.toString())
                val noteArrayJsonResponse = body!!.source()

                val moshi: Moshi = Moshi.Builder().build()
                val type = Types.newParameterizedType(List::class.java, Take::class.java)
                val adapter = moshi.adapter<List<Take>>(type)
                val notes : List<Take>? = adapter.fromJson(noteArrayJsonResponse)

                if (notes != null) {
                    callback(notes)
                }
            }
        }
    })
}

fun upvote(id:String){
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply { put("hello","hello") }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
    val request = Request.Builder().post(requestBody).url("http://104.197.168.4/takes/upvote/"+id).build()
    client.newCall(request).enqueue(object: Callback{
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if(!response.isSuccessful){
                throw IOException("Unexpected code $response")
            }else{
                val body = response.body

            }
        }
    })

}

fun downvote(id:String){
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply { put("hello","hello") }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
    val request = Request.Builder().post(requestBody).url("http://104.197.168.4/takes/downvote/"+id).build()
    client.newCall(request).enqueue(object: Callback{
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if(!response.isSuccessful){
                throw IOException("Unexpected code $response")
            }else{
                val body = response.body

            }
        }
    })

}

fun post(id:String, text:String){
    val client = OkHttpClient()
    val jsonObject = JSONObject().apply { put("text",text) }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
    val request = Request.Builder().post(requestBody).url("http://104.197.168.4/takes/"+id).build()
    client.newCall(request).enqueue(object: Callback{
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if(!response.isSuccessful){
                throw IOException("Unexpected code $response")
            }else{
                val body = response.body

            }
        }
    })
}

fun getTakesByUser(id:String, callback : (List<Take>) -> Unit){
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://104.197.168.4/users/"+id)
        .get()
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body
                val noteArrayJsonResponse = body!!.source()
                val moshi: Moshi = Moshi.Builder().build()
                val type = Types.newParameterizedType(List::class.java, Take::class.java)
                val adapter = moshi.adapter<List<Take>>(type)
                val notes : List<Take>? = adapter.fromJson(noteArrayJsonResponse)

                if (notes != null) {
                    callback(notes)
                }
            }
        }
    })
}

fun postUser(username:String, password:String, callback : (UserIn) -> Unit){
    val client = OkHttpClient()
    var jsonObject = JSONObject().apply {
        put("username",username).put("password",password)
    }
    val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
    val request = Request.Builder().post(requestBody).url("http://104.197.168.4/users/add").build()
    client.newCall(request).enqueue(object: Callback{
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if(!response.isSuccessful){
                throw IOException("Unexpected code $response")
            }else{
                val body = response.body
                val moshi : Moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<UserIn> =moshi.adapter(UserIn::class.java)
                val userBack = adapter.fromJson(body!!.source())

                if (userBack != null) {
                    callback(userBack)
                }
                else{
                    print("No user returned")
                }
            }
        }
    })
}
