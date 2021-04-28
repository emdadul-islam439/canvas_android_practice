package com.example.canvasandroidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import com.example.canvasandroidpractice.custom_fan_controller_classes.*
import com.google.android.material.tabs.TabLayout

//val TAG = "emdad"

class CustomFanControllerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_fan_controller)
        var dialView = findViewById<DialView>(R.id.dialView)
        dialView.setOnClickListener {
            Toast.makeText(this, "TAPPED!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
        val ami = TAG
        Log.d("emdad", "onTouchEvent: MAIN TOUCH.........>>>>>>>")
    }
}