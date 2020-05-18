package com.hoony.kotlinsample.custom_view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hoony.kotlinsample.R

class EmotionalFaceView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    // Add two constants, one for the HAPPY state and one for the SAD state.
    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    // Setup default values of the XML attribute properties, in case a user of the custom
    // view does not set one of them.

    // Some colors for the background, eyes and mouth.
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    // Face border width in pixels.
    private var borderWidth = DEFAULT_BORDER_WIDTH

    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mouthPath = Path()
    // View size in pixels.
    private var size = 0

    // Add a new property called happinessStyle for rhe face happiness state.
    var happinessState = HAPPY
        set(state) {
            field = state
            // Call the invalidate() method in the set happinessState method. The invalidate()
            // method makes Android redraw the view by calling onDraw()
            invalidate()
        }

    // Call a new private setupAttributes() method from the init block.
    init {
        paint.isAntiAlias = true
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        // Obtain a typed array of the XML attributes.
        val typedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView, 0, 0)

        // Extract custom attributes into member variables.
        happinessState =
            typedArray.getInt(R.styleable.EmotionalFaceView_state, HAPPY.toInt()).toLong()
        faceColor = typedArray.getColor(R.styleable.EmotionalFaceView_faceColor, DEFAULT_FACE_COLOR)
        eyesColor = typedArray.getColor(R.styleable.EmotionalFaceView_eyesColor, DEFAULT_EYES_COLOR)
        mouthColor =
            typedArray.getColor(R.styleable.EmotionalFaceView_mouthColor, DEFAULT_MOUTH_COLOR)
        borderColor =
            typedArray.getColor(R.styleable.EmotionalFaceView_borderColor, DEFAULT_BORDER_COLOR)
        borderWidth =
            typedArray.getDimension(R.styleable.EmotionalFaceView_borderWidth, DEFAULT_BORDER_WIDTH)

        // Recycle the typedArray to make the data associated with it ready for garbage collection.
        typedArray.recycle()
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        0
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

        // Clear
        mouthPath.reset()

        mouthPath.moveTo(size * 0.22f, size * 0.7f)

        if (happinessState == HAPPY) {
            // Draw a happy mouth path by using quadTo() method as you learned before.
            mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)
            mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f)
        } else {
            // Draw a sad mouth path.
            mouthPath.quadTo(size * 0.50f, size * 0.50f, size * 0.78f, size * 0.70f)
            mouthPath.quadTo(size * 0.50f, size * 0.60f, size * 0.22f, size * 0.70f)
        }

        paint.color = mouthColor
        paint.style = Paint.Style.FILL

        // Draw mouth path
        canvas.drawPath(mouthPath, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // Calculate the smaller dimension of your view
        size = Math.min(measuredWidth, measuredHeight)
        // Use setMeasuredDimension(Int, Int) to store the measured width and measured height of
        // the view, in this case making your view width and height equivalent.
        setMeasuredDimension(size, size)
    }
}