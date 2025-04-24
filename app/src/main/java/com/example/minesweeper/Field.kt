package com.example.minesweeper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.WindowManager
import kotlin.math.min
import kotlin.random.Random

@SuppressLint("ServiceCast")
class Field(private var size: Int, private var count:Int, var context: Context) {
    private var field: Array<Array<Cell>> = Array(size) {Array(size) {Cell()} }
    private var delta: Float = 0F
    private var screenW: Int = 0
    private var screenH: Int = 0
    private var result:Int = -1

    init {
        val displayMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        screenW = screenWidth
        screenH = screenHeight


        delta = (min(screenWidth, screenHeight) / (size + 2)).toFloat()

        for (i in 0 until size) {
            for (j in 0 until size) {
                field[i][j] = Cell((i + 1) * delta, (j + 1) * delta, delta, 0)
            }
        }

        var placedMines = 0
        while (placedMines < count) {
            val row = Random.nextInt(size)
            val col = Random.nextInt(size)

            if (field[row][col].value == 0 && !(row == 0 && col == 0)) {
                field[row][col].value = -1
                placedMines++
                for (i in -1..1) {
                    for (j in -1..1) {
                        val newRow = row + i
                        val newCol = col + j
                        if (newRow in 0 until size && newCol in 0 until size && field[newRow][newCol].value != -1) {
                            field[newRow][newCol].value++
                        }
                    }
                }
            }
        }
        for (i in 0 until size) {
            for (j in 0 until size) {
                Log.i("field", field[i][j].value.toString())
            }
            Log.i("field", " ")
        }
    }

    fun draw(canvas: Canvas) {
        Log.i("result", result.toString())
        Log.i("result", "will draw field")
        for (i in 0 until size) {
            for (j in 0 until size) {
                field[i][j].draw(canvas)
            }
        }
        if (result == 0) {
            Log.i("result", "printing Lose")
            val paintText = Paint()
            paintText.color = Color.BLACK
            paintText.textSize = 100F
            canvas.drawText("Oops.. You lost", 120F, (size + 2) * delta, paintText)
        }
        if (result == 1){
            Log.i("result", "printing Win")
            val paintText = Paint()
            paintText.color = Color.BLACK
            paintText.textSize = 100F
            canvas.drawText("Congratulations!", 120F, (size + 2) * delta, paintText)
        }
    }

    private fun revealCell(row: Int, col: Int) {
        if (row !in 0 until size || col !in 0 until size || field[row][col].revealed) return
        field[row][col].revealed = true
        if (field[row][col].value == 0) {
            for (i in -1..1) {
                for (j in -1..1) {
                    revealCell(row + i, col + j)
                }
            }
        }
    }

   private fun isGameWon(): Boolean {
       Log.i("result", "in isGameWon")
       for (row in 0 until size) {
           for (col in 0 until size) {
               if (field[row][col].value != -1 && !field[row][col].revealed) {
                   Log.i("result", "found not opened")
                   return false
               }
           }
       }
       Log.i("result", "will return gameIsWon")
       return true
   }
    fun event(event: MotionEvent) {
        if (result != -1) return
        val row = (event.x / delta).toInt() - 1
        val col = (event.y / delta).toInt() - 1

        if (row !in 0 until size || col !in 0 until size) return

        if (field[row][col].value == -1) {
            result = 0
        } else {
            revealCell(row, col)
            if (isGameWon()) {
                Log.i("result", "will make result 1")
                result = 1
            }
        }

    }
}