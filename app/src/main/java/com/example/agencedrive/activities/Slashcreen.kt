package com.example.agencedrive.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                    Thread.sleep(50)  //
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                count += 1
            }
            runOnUiThread {
                val preference = Preference.Preference(this,PreferencesConstant.PREFERENCE_NAME)
                val email =  preference.getStringPreferenceValue(PreferencesConstant.PREFERENCE_EMAIL_USER)

                if (email.isNullOrEmpty()   ){
                    val intent= Intent(this , LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
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