package com.example.agencedrive.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.agencedrive.R
import com.example.agencedrive.services.AuthentificationServiceActivity
import com.example.agencedrive.services.FirebaseUserServices
import com.google.android.material.textfield.TextInputLayout

lateinit var singup: TextView
lateinit var emailedit: EditText
lateinit var passwordedit: TextInputLayout
lateinit var buttonedit: Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singin)
        singup = findViewById(R.id.singupTextViewAgen)
        initializeviews()
        textviewsingup()
        inputRedirection()
    }

    private fun textviewsingup() {
        singup.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
    private fun initializeviews() {
        setContentView(R.layout.activity_singin)
        passwordedit = findViewById(R.id.agencePassword)
        emailedit = findViewById(R.id.agenceEmail)
        buttonedit = findViewById(R.id.agenceButton)
        singup = findViewById(R.id.singupTextViewAgen)
    }

    private fun inputRedirection() {
        buttonedit.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("one moment...")
            progressDialog.show()
            if (passwordedit.editText!!.text.toString().isNotEmpty() && emailedit.text.toString()
                    .isNotEmpty()
            ) {
                val email = emailedit.text.toString()
                val password = passwordedit.editText!!.text.toString()
                FirebaseUserServices.verifyUserForLogin(email, password, this, progressDialog)
            }else{
                progressDialog.dismiss()
                Toast.makeText(this, "entrez vos donnees de connexion", Toast.LENGTH_SHORT).show()
            }
            }
        }
    }

