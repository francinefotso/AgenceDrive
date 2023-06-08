package com.example.agencedrive.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.agencedrive.R
import com.example.agencedrive.models.Agences
import com.example.agencedrive.services.FirebaseUserServices
import com.google.android.material.textfield.TextInputLayout


lateinit var singin: TextView
lateinit var singinflech: ImageView

lateinit var user_first_name: EditText
lateinit var user_last_name: EditText
lateinit var user_phone_number: EditText

lateinit var emaileditext: EditText
lateinit var passwordeditext: TextInputLayout
lateinit var passworconfideditext: TextInputLayout
lateinit var RegisterButton: Button
lateinit var Reglog: TextView


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        singin = findViewById(R.id.singinTextViewRegAgen)
        singinflech = findViewById(R.id.flech)
        initializeViews()
        textviewSingUp()
        imageviewSingUp()
        infoRegister()

    }

    private fun imageviewSingUp() {
        singinflech.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun textviewSingUp() {
        singin.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }


    private fun initializeViews() {
        setContentView(R.layout.activity_sing_up)
        user_first_name = findViewById(R.id.RegAgenFistName)
        user_last_name = findViewById(R.id.RegAgenLastName)
        user_phone_number = findViewById(R.id.RegAgePhon)
        emaileditext = findViewById(R.id.regAgeEmail)
        passwordeditext = findViewById(R.id.RegAgenPassword)
        passworconfideditext = findViewById(R.id.regAgenConfPassw)
        RegisterButton = findViewById(R.id.regButton)
        Reglog = findViewById(R.id.singinTextViewRegAgen)
        singup = findViewById(R.id.singinTextViewRegAgen)
    }

//    @SuppressLint("SuspiciousIndentation")
    private fun infoRegister() {
        RegisterButton.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("one moment ...")
            progressDialog.show()

            val firstName = user_first_name.text.toString()
            val lastName = user_last_name.text.toString()
            val phone = user_phone_number.text.toString()
            val email = emaileditext.text.toString()
            val password = passwordeditext.editText?.text.toString()
            val passconfirm = passworconfideditext.editText?.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passconfirm.isNotEmpty())  {

                if (password.equals(passconfirm)) {
                    if(isValid(email)){
                        val agences = Agences(firstName, lastName, phone, email, password)

                        FirebaseUserServices.verifyUserForRegister(agences, this, progressDialog)
                        Log.i("{i}", "{${agences.email}}")
                    }else {
                        Toast.makeText(
                            this, "Email incorrect",
                            Toast.LENGTH_SHORT
                        ).show();

                    }
                } else {
                    Toast.makeText(
                        this, "This passwords are differents",
                        Toast.LENGTH_SHORT
                    ).show();

                }

            } else {
                Toast.makeText(
                    this, "veuillez remplir tous les champs",
                    Toast.LENGTH_SHORT
                ).show();
            }
        }


    }


    private fun isValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
