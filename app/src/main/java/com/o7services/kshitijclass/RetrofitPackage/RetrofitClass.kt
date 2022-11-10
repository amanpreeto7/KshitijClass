package com.o7services.kshitijclass.RetrofitPackage

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClass {
    lateinit var apiInterface : ApiInterface

        public fun getInstance(): ApiInterface {
            if (this::apiInterface.isInitialized == false) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://gorest.co.in/public/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiInterface = retrofit.create(ApiInterface::class.java)
            }

            return apiInterface


    }
}