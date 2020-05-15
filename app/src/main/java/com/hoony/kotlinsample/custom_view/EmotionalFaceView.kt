package com.hoony.kotlinsample.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView : View {

    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // Some colors for the background, eyes and mouth.
    private val faceColor = Color.YELLOW
    private val eyesColor = Color.BLACK
    private val mouthColor = Color.BLACK
    private val borderColor = Color.BLACK
    // Face border width in pixels.
    private val borderWidth = 4.0f
    // View size in pixels.
    private val size = 320

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        // Call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    private fun drawFaceBackground(canvas: Canvas) {
        // Set the paint color to the faceColor and make it fill the drawing area.
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        // Calculate a radius for a circle which you want to draw as the face background.
        val radius = size / 2f

        // Draw the background circle with a center of (x,y), where x and y are equal to the
        // half of size, and with the calculated radius.
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        // Change the paint color to the borderColor and make if just draw a border around the drawing
        // area by setting the style to STROKE
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        // Draw a border with the same center but with a radius shorter than the previous radius bt the borderWidth.
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        // Set the paint color to the eyesColor and make it fill he drawing area.
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        // Create a RectF object with left, top, right and bottom using the following percentages
        // of the size: (32%, 23%, 43%, 50%). Then you draw the left eye by drawing an oval with
        // the create RectF.
        // For more info about RectF, check the https://developer.android.com/reference/android/graphics/RectF
        val leftEyeRect = RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)

        // DO the same as the last step but with the following percentages of size : (57%, 23%, 68%, 50%)
        val rightEyeRect = RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)
    }

    private fun drawMouth(canvas: Canvas) {

    }
}