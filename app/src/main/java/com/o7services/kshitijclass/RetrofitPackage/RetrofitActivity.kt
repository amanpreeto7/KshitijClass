package com.o7services.kshitijclass.RetrofitPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.o7services.kshitijclass.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    lateinit var retrofitClass: RetrofitClass
    private  val TAG = "RetrofitActivity"
    lateinit var btnAddPost: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        btnAddPost = findViewById(R.id.btnAddPost)
        retrofitClass = RetrofitClass()
//        throw RuntimeException("Test Crash") // Force a crash

      val inExecute =  retrofitClass.getInstance().getUsers()
          .enqueue(object: Callback<ArrayList<UsersResponse>>{
              override fun onResponse(
                  call: Call<ArrayList<UsersResponse>>,
                  response: Response<ArrayList<UsersResponse>>
              ) {
                  Log.e(TAG, " in response success")
              }

              override fun onFailure(call: Call<ArrayList<UsersResponse>>, t: Throwable) {
                  Log.e(TAG, " in response failure")
              }

          }

      )

        btnAddPost.setOnClickListener {
            var map = HashMap<String, Any>()
            map["title"] = "Testing"
            map["body"] = "Testing"
            retrofitClass.getInstance().addPost("Bearer 3ee949f72c6f36c0d90a9cb2b4cbcb91176626fcbf387a2daa2894750686de9e",
            /*"Testing", "Testing Body"*/ map ).enqueue(object: Callback<AddPostResponse>{
                override fun onResponse(
                    call: Call<AddPostResponse>,
                    response: Response<AddPostResponse>
                ) {

                }

                override fun onFailure(call: Call<AddPostResponse>, t: Throwable) {
                        Log.e(TAG, " in failure ${t.localizedMessage}")
                }
            })
        }
        Log.e(TAG, " after execute ${inExecute}")
    }
}