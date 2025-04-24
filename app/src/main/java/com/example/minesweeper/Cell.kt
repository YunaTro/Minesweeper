package com.example.minesweeper

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log

class Cell(private var x: Float = 0F, private var y: Float = 0F, private var size: Float = 0F, var value: Int = 0) {
    var revealed: Boolean = false
    fun draw(canvas: Canvas) {
        Log.i("cell", "will draw cell $revealed")
        val p = Paint()
        if (revealed) p.color = Color.argb(200, 240, 200, 117)
        else p.color = Color.argb(200, 102, 255, 102)
        canvas.drawRect(x, y, x + size, y + size, p)
        Log.i("cell", "drew rect $x $y")
        if (revealed && value != 0) {
            val paintText = Paint()
            paintText.textSize = 50F
            paintText.color = chooseColor(value)
            canvas.drawText(value.toString(), x + size / 2,y + size / 2, paintText)
        }

        p.color = Color.argb(200, 0, 0, 0)
        p.style = Paint.Style.STROKE
        canvas.drawRect(x, y, x + size, y + size, p)
        Log.i("cell", "drew bounds $x $y")
    }

    fun chooseColor(value: Int): Int {
        when (value) {
            1 -> {
                return Color.BLUE
            }
            2 -> {
                return Color.argb(200, 0, 102, 0)
            }
            3 -> {
                return Color.RED
            }
            4 -> {
                return Color.MAGENTA
            }
            5 -> {
                return Color.YELLOW
            }
            6 -> {
                return Color.CYAN
            }
        }
        return Color.BLACK
    }
}