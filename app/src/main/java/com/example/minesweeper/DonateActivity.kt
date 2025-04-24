package com.example.minesweeper

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DonateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)
    }

    fun onClick(view: View?) {
        if (view != null) {
            if (view.id == R.id.btn_home) {
                finish()
            }
        }
    }
}