package com.o7services.kshitijclass.RetrofitPackage

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("users")
    fun getUsers(): Call<ArrayList<UsersResponse>>

    @POST("users/7/posts")
    @FormUrlEncoded
    fun addPost(@Header("Authorization") token: String,
               // @Field("title") title: String,
               // @Field("bodyyy") body : String,
                @FieldMap field: HashMap<String, Any>
    ):Call<AddPostResponse>
}