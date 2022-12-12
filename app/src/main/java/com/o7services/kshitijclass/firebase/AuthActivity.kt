package com.o7services.kshitijclass.firebase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.o7services.kshitijclass.R
import java.util.*


class AuthActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var callbackManager: CallbackManager
    lateinit var btnLogin: Button
    lateinit var fbLogin: LoginButton
    private  val TAG = "AuthActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        btnLogin = findViewById(R.id.btnLogin)
        fbLogin = findViewById(R.id.login_button)
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)


        auth.currentUser?.let {
            firebaseUser = it
            System.out.println(" in auth current user ${firebaseUser.uid}")
            btnLogin.setText(resources.getString(R.string.logout))

        }?:kotlin.run {
            btnLogin.setText(resources.getString(R.string.login))

        }

        //facebook login
        callbackManager = CallbackManager.Factory.create();
        fbLogin.setReadPermissions(Arrays.asList("email"));

        fbLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                System.out.println("in error $exception")
            }

            override fun onSuccess(result: LoginResult?) {
                System.out.println("in success")
            }
        })
    }

    public fun buttonclick(view: View){
        System.out.println("in button click")
        if( auth.currentUser != null){
            mGoogleSignInClient.signOut()
            auth.signOut()
            btnLogin.setText(resources.getString(R.string.login))

        }else {
            val signInIntent: Intent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, 1)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        }

    }


    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
               System.out.println( account.email.toString())
                System.out.println( account.displayName.toString())
                btnLogin.setText(resources.getString(R.string.logout))
            }
        }
    }
}