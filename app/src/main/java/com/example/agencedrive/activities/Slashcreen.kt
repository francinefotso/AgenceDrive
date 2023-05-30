package com.example.agencedrive.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.agencedrive.R

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
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }).start()
    }

}