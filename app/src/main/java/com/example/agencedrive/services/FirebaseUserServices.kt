package com.example.agencedrive.services

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.agencedrive.activities.LoginActivity
import com.example.agencedrive.models.Agences
import com.example.agencedrive.models.Publications
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class FirebaseUserServices {
    companion object {
//    val db = Firebase.firestore
    private var db = FirebaseFirestore.getInstance()
        private val PUBLICATIONS_COLLECTIONS ="Publications"
    private val AGENCE_COLLECTION_NAME = "Agences"


    fun verifyUserForRegister(
        agences: Agences,
        context: Context,
        progressDialog: ProgressDialog
    ) {
        db.collection(AGENCE_COLLECTION_NAME)
            .whereEqualTo("email", agences.email)
            .get()
            .addOnSuccessListener {
                if (it.documents.isEmpty()) {
                    AuthentificationServiceActivity.createUser(agences, context, progressDialog)
//                    Log.i("","{${agences.email}}")
                    Log.i("","{${agences.phone}}")
//                    Log.i("","${agences.passconfirm}")
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(
                        context,
                        "Cet utilisateur existe deja",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

//        fun publication (publications: Publications){
//
//            db.collection(PUBLICATIONS_COLLECTIONS).get().addOnSuccessListener {
//                if (it.documents.isEmpty())
//            }else{
//
//            }
//
//
//        }

    fun verifyUserForLogin(
        email: String,
        password: String,
        context: Context,
        progressDialog: ProgressDialog
    ) {
        db.collection(AGENCE_COLLECTION_NAME)
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener {
                if (it.documents.isNotEmpty()) {
                    AuthentificationServiceActivity.connectUser(
                        email,
                        password,
                        context,
                        progressDialog
                    )
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(
                        context,
                        "Cet utilisateur n'existe pas",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }


        fun addUser(agences: Agences, context: Context, progressDialog: ProgressDialog) {
            db.collection(AGENCE_COLLECTION_NAME).add(agences).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.i("{u}","{${agences.email}}")
                    progressDialog.dismiss()
                    context.startActivity(Intent(context, LoginActivity::class.java))
                    (context as Activity).finish()
                    Toast.makeText(context,"Agence creer avec succes",Toast.LENGTH_LONG).show()
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(context, "erreur de creation", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}