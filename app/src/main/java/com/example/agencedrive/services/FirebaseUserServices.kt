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
        var user = mutableListOf<Agences>()
        private val USER_COLLECTION_NAME = "Users"

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
//                    Log.i("","{${agences.phone}}")
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

        fun displayAgence(context: Context) {
            (context as Activity).runOnUiThread {
                FirebaseFirestore.getInstance().collection(USER_COLLECTION_NAME).get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            document.data.values
                            if (document.data.isNotEmpty()) {
                                var response =
                                    try {
                                        document
                                    } catch (ex: Exception) {
                                        null
                                    }
                                if (response != null) {
                                    var u = Agences(
                                        response.data.get("firstName").toString(),
                                        response.data.get("lastName").toString(),
                                        response.data.get("email").toString(),
                                        response.data.get("password").toString(),
                                        response.data.get("phoneNumber").toString()
                                    )
                                    Log.i("user 1", user.toString())
                                    user.add(u)
                                    Log.i("user 2", "${document.data.values}")
                                } else {

                                }
                            }
                        }
                    }.addOnSuccessListener {

                        Toast.makeText(context, "", Toast.LENGTH_SHORT)
                    }
            }
            Log.i("{user end}", "${user.toString()}")
        }
        fun getAgency(): MutableList<Agences> {
            return user
            Log.i("{}user", "$user")

        }
    }
}