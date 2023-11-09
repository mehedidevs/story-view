package com.example.my_stories.demo

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.RectF
import android.graphics.drawable.Drawable

internal class ProgressDrawable(val NUM_SEGMENTS:Int,   private val mForeground: Int, private val mBackground: Int) :
    Drawable() {
    private val mPaint = Paint()
    private val mSegment = RectF()
    override fun onLevelChange(level: Int): Boolean {
        invalidateSelf()
        return true
    }

    override fun draw(canvas: Canvas) {
        val level = level / 10000f
        val b = bounds
        val gapWidth = b.height() / 2f
        val segmentWidth = (b.width() - (NUM_SEGMENTS - 1) * gapWidth) / NUM_SEGMENTS
        mSegment[0f, 0f, segmentWidth] = b.height().toFloat()
        mPaint.color = mForeground
        for (i in 0 until NUM_SEGMENTS) {
            val loLevel = i / NUM_SEGMENTS.toFloat()
            val hiLevel = (i + 1) / NUM_SEGMENTS.toFloat()
            if (loLevel <= level && level <= hiLevel) {
                val middle = mSegment.left + NUM_SEGMENTS * segmentWidth * (level - loLevel)
                canvas.drawRect(mSegment.left, mSegment.top, middle, mSegment.bottom, mPaint)
                mPaint.color = mBackground
                canvas.drawRect(middle, mSegment.top, mSegment.right, mSegment.bottom, mPaint)
            } else {
                canvas.drawRect(mSegment, mPaint)
            }
            mSegment.offset(mSegment.width() + gapWidth, 0f)
        }
    }

    override fun setAlpha(alpha: Int) {}
    override fun setColorFilter(cf: ColorFilter?) {}
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }


}