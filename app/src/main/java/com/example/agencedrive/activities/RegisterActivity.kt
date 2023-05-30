package com.example.agencedrive.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.agencedrive.R
import com.example.agencedrive.services.AuthentificationServiceActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.ActionCodeEmailInfo
import java.util.regex.Pattern

lateinit var singin: TextView
lateinit var singinflech: ImageView
lateinit var usereditext: EditText
lateinit var emaileditext: EditText
lateinit var passwordeditext:  TextInputLayout
lateinit var passworconfideditext: TextInputLayout
lateinit var RegisterButton: Button
lateinit var Reglog: TextView



class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        singin=findViewById(R.id.singinTextViewRegAgen)
        singinflech=findViewById(R.id.flech)
        initializeViews()
        textviewSingUp()
        imageviewSingUp()
        infoRegister()

    }

    private fun imageviewSingUp(){
        singinflech.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        }

    private fun textviewSingUp(){
        singin.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
}


    private fun initializeViews(){
        setContentView(R.layout.activity_sing_up)
        usereditext= findViewById(R.id.RegAgenUser)
        emaileditext=findViewById(R.id.regAgeEmail)
        passwordeditext=findViewById(R.id.RegAgenPassword)
        passworconfideditext=findViewById(R.id.regAgenConfPassw)
        RegisterButton=findViewById(R.id.regButton)
        Reglog=findViewById(R.id.singinTextViewRegAgen)
        singup = findViewById(R.id.singinTextViewRegAgen)
    }

    private fun infoRegister(){
        RegisterButton.setOnClickListener{
            if (usereditext.text.isNotEmpty() && emaileditext.text.isNotEmpty() &&
                passwordeditext.editText!!.text.isNotEmpty()&& passworconfideditext.editText!!.text.isNotEmpty()&&
                isValid(emaileditext.text.toString()))
            {
                if (passwordeditext.editText!!.text.toString().equals(passworconfideditext.editText!!.text.toString()) ){
                    AuthentificationServiceActivity.createUser(usereditext.text.toString(),emaileditext.text.toString(),
                        passwordeditext.editText!!.text.toString(),
                        passworconfideditext.editText!!.text.toString(),this)
                    Intent(this,HomeActivity::class.java).also {
                        startActivity(it);
                        finish();
                    }
                }
                else{
                    Toast.makeText(
                        this, "This passwords are differents",
                        Toast.LENGTH_SHORT
                    ).show();
                }

            } else {
                Toast.makeText(
                    this, "Invalid email",
                    Toast.LENGTH_SHORT
                ).show();
            }

            }
        }


    }
private fun isValid(email:String):Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
