package com.example.agencedrive.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.agencedrive.R
import com.example.agencedrive.constants.PreferencesConstant
import com.example.agencedrive.utilities.Preference

class Slashcreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeCount()
    }
    private fun initializeCount(){
        var count:Int=0
        Thread(Runnable {
            while (count < 100) {
                try {
                    Log.i("{}message","run")
                    Thread.sleep(50)  //
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                count += 1
            }
            runOnUiThread {
                Log.i("{}message","run")
                val preference = Preference.Preference(this,PreferencesConstant.PREFERENCE_NAME)
                val email =  preference.getStringPreferenceValue(PreferencesConstant.PREFERENCE_EMAIL_USER)

                if (email.isNullOrEmpty()   ){
                    Log.i("{}message","login")
                    val intent= Intent(this , LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Log.i("{}message","home")
                    val intent= Intent(this , HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
//                var intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }).start()
    }

}