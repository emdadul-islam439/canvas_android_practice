package com.example.canvasandroidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.canvasandroidpractice.clipping_example_classes.ClippedView

class ClippingExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ClippedView(this))
    }
}