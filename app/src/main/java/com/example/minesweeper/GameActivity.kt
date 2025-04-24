package com.example.minesweeper

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.btn_home) {
                finish()
            }
            if (v.id == R.id.btn_restart) {
                finish()
                val intent: Intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
            }
        }
    }
}