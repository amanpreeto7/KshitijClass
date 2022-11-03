package com.o7services.kshitijclass.apppermissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.o7services.kshitijclass.R
import com.o7services.kshitijclass.databinding.ActivityAppPermissionsBinding

class AppPermissions : AppCompatActivity() {
    lateinit var binding: ActivityAppPermissionsBinding
    private  val TAG = "AppPermissions"
    var getPermissions = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){
            if(it){
                getImages.launch("image/*")
            }else{
            }
    }

    var getMultiplePermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){
        for((key, value) in it){
            Log.e(TAG, "key $key value $value")
            if(key == Manifest.permission.WRITE_EXTERNAL_STORAGE){

            }
        }
    }

    var getImages = registerForActivityResult(
        ActivityResultContracts.GetContent()){
        binding.imageView.setImageURI(it)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppPermissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSinglePermission.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

            }else{
            getPermissions.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        }

        binding.btnMultiplePermission.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED){

            }else{
                getMultiplePermissions.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS))
        }
        }
    }
}