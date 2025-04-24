package com.example.minesweeper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.btn_donate -> {
                    val intent: Intent = Intent(this, DonateActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_exit -> {
                    finish()
                }
                R.id.btn_start -> {
                    val intent: Intent = Intent(this, GameActivity::class.java)
                    startActivity(intent)
                }
                R.id.main -> {
                    Toast.makeText(this, "Missed haha", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}