package com.o7services.kshitijclass.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.facebook.FacebookSdk
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.o7services.kshitijclass.R
import com.o7services.kshitijclass.databinding.ActivityEmailAuthBinding

class EmailAuthActivity : AppCompatActivity() {
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityEmailAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FacebookSdk.sdkInitialize(getApplicationContext());

        auth = Firebase.auth
        checkSignin()

        binding.etEmail.doOnTextChanged { text, _, _, _ ->
            var inputText = text
            if(inputText.isNullOrEmpty() == false){
                binding.tilEmail.error = null
            }
        }

    }

    fun checkSignin(){
        auth.currentUser?.let {
            firebaseUser = it
            System.out.println(" in auth current user ${firebaseUser.uid}")
            binding.btnLogin.setText(resources.getString(R.string.logout))

        }?:kotlin.run {
            binding.btnLogin.setText(resources.getString(R.string.login))

        }
    }

    fun buttonclick(view: View) {
        if(auth.currentUser != null)
        {
            auth.signOut()
            checkSignin()
            return
        }
        if (binding.etEmail.text.toString().isNullOrEmpty()){
            binding.tilEmail.error = "Enter email"
        }else
        if (binding.etPassword.text.toString().isNullOrEmpty()){
            binding.tilPassword.error = "Enter password"
        }else{

            auth.signInWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful)
                        checkSignin()
                    else{
                        System.out.println(" in error ${it.exception}")
                    }
            }
        /*auth.createUserWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful)
                        checkSignin()
                    else{
                        System.out.println(" in error ${it.exception}")
                    }
            }*/
        }
    }
}