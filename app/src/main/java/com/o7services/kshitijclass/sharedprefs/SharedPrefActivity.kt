package com.o7services.kshitijclass.sharedprefs

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.o7services.kshitijclass.R

class SharedPrefActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var etName: EditText
    lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref)
        sharedPrefs = getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        editor = sharedPrefs.edit()
        etName = findViewById(R.id.etName)
        btnSave = findViewById(R.id.btnSave)

        if(sharedPrefs.contains("name")){
            etName.setText(sharedPrefs.getString("name", ""))
        }
        btnSave.setOnClickListener {
            if(etName.text.toString().isNullOrEmpty()){
                Toast.makeText(this, "enter name", Toast.LENGTH_LONG).show()
            }else{
                editor.putString("name", etName.text.toString())
                editor.apply()
                editor.commit()
            }
        }

    }
}