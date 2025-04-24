package com.example.minesweeper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var field: Field? = null

    init {
        field = Field(10, 18, getContext())
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        field?.draw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        field?.event(event)
        invalidate()
        return true
    }

}