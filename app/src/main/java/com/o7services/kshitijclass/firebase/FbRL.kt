package com.o7services.kshitijclass.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.o7services.kshitijclass.R

class FbRL : AppCompatActivity() {
    val database = Firebase.database
    private  val TAG = "FbRL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fb_rl)
        val myRef1 = database.getReference("SoDNRYyxPaSSsnrhKXVeJxAKK7b2")
        val myRef = database.getReference("-MyzGKY4ZjgQckVfz-sR")
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, "Value is: " + snapshot.value)

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<HashMap<String, String>>()

                Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        myRef1.setValue("1234")
        Handler(Looper.getMainLooper()).postDelayed({
                    myRef1.removeValue()
        },1000)
    }
}