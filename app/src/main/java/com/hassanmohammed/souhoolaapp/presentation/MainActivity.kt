package com.hassanmohammed.souhoolaapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hassanmohammed.souhoolaapp.R
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}