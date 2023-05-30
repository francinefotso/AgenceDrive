package com.example.agencedrive.services

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.agencedrive.R
import com.example.agencedrive.activities.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthentificationServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification_service)
    }


    companion object{
        private val auth: FirebaseAuth = Firebase.auth
        private val TAG = "FirebaseAuthentication"

        fun createUser(RegAgenUser:String, regAgeEmail:String, RegAgenPassword:String, regAgenConfPassw:String, context: Context){
            auth.createUserWithEmailAndPassword(regAgeEmail, regAgenConfPassw)
                .addOnCompleteListener((context as Activity))
            { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        context.startActivity(Intent(context, HomeActivity::class.java))
                        context.finish()
                        val user = auth.currentUser

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

        fun connectUser(regAgeEmail: String, regAgenConfPassw: String, context: Context) {
            auth.createUserWithEmailAndPassword(regAgeEmail, regAgenConfPassw)
                .addOnCompleteListener((context as Activity))

                { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(
                            context,
                            "Authentication successful.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        context.startActivity(Intent(context, HomeActivity::class.java))
                        context.finish()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

        fun logout(){
            auth.signOut()
        }
    }
}