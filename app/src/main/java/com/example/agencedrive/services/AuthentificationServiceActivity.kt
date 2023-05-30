package com.example.agencedrive.services

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.agencedrive.R
import com.example.agencedrive.activities.HomeActivity
import com.example.agencedrive.constants.PreferencesConstant
import com.example.agencedrive.models.Agences
import com.example.agencedrive.utilities.Preference
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


        fun createUser(agences: Agences, context: Context, progressDialog: ProgressDialog) {
            auth.createUserWithEmailAndPassword(agences.email , agences.password)
                .addOnCompleteListener((context as Activity)) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Log.i("","{${agences.email}}")
                        Log.i("{}","${agences.password}")
                        FirebaseUserServices.addUser(agences, context, progressDialog)
                        val user = auth.currentUser

                        Toast.makeText(context, "utilisateur creer", Toast.LENGTH_SHORT)
                    } else {
                        progressDialog.dismiss()
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    }
                }
        }

        fun connectUser(
            email: String,
            password: String,
            context: Context,
            progressDialog: ProgressDialog
        ) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((context as Activity))

                { task ->
                    if (task.isSuccessful) {
                        progressDialog.dismiss()
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser

                        val preference = Preference.Preference(context, PreferencesConstant.PREFERENCE_NAME)
                        preference.addPreference(PreferencesConstant.PREFERENCE_EMAIL_USER, email)

                        context.startActivity(Intent(context, HomeActivity::class.java))
                        context.finish()
                    } else {
                        progressDialog.dismiss()
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context, "utilisateur non existant", Toast.LENGTH_SHORT
                        )
                    }


                }
        }

//        fun createUser(RegAgenUser:String, regAgeEmail:String, RegAgenPassword:String, regAgenConfPassw:String, context: Context){
//            auth.createUserWithEmailAndPassword(regAgeEmail, regAgenConfPassw)
//                .addOnCompleteListener((context as Activity))
//            { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "createUserWithEmail:success")
//                        context.startActivity(Intent(context, HomeActivity::class.java))
//                        context.finish()
//                        val user = auth.currentUser
//
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            context,
//                            "Authentication failed.",
//                            Toast.LENGTH_SHORT,
//                        ).show()
//                    }
//                }
//        }

//        fun connectUser(regAgeEmail: String, regAgenConfPassw: String, context: Context, progressDialog: ProgressDialog) {
//            auth.createUserWithEmailAndPassword(regAgeEmail, regAgenConfPassw)
//                .addOnCompleteListener((context as Activity))
//
//                { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "createUserWithEmail:success")
//                        val user = auth.currentUser
//                        Toast.makeText(
//                            context,
//                            "Authentication successful.",
//                            Toast.LENGTH_SHORT,
//                        ).show()
//                        context.startActivity(Intent(context, HomeActivity::class.java))
//                        context.finish()
//
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(
//                            context,
//                            "Authentication failed.",
//                            Toast.LENGTH_SHORT,
//                        ).show()
//                    }
//                }
//        }




        fun logout(){
            auth.signOut()
        }
    }
}