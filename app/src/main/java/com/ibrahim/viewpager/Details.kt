package com.ibrahim.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val detail = getIntent().getStringExtra("detail")
        var detailText: TextView = findViewById(R.id.detailText)
        detailText.text = detail
    }
}