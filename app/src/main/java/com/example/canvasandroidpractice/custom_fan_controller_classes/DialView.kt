package com.example.canvasandroidpractice.custom_fan_controller_classes

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.canvasandroidpractice.R
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.min
import kotlin.math.sin


//val TAG = "emdad"

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    init {
        Log.d(TAG, "FANSPEED_init: label = $label")
    }

    fun next() : FanSpeed {
        Log.d(TAG, "FANSPEED_next: this = $this")
        return when (this) {
            OFF -> LOW
            LOW -> MEDIUM
            MEDIUM -> HIGH
            HIGH -> OFF
        }
    }
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

private var radius = 0.0f                   // Radius of the circle.
private var fanSpeed = FanSpeed.OFF         // The active selection.
// position variable which will be used to draw label and indicator circle position
private val pointPosition: PointF = PointF(0.0f, 0.0f)

class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }

    init {
        Log.d(TAG, "DialView_init: context = ${context}  attrs = $attrs  defStyleAttr = $defStyleAttr")
//        isClickable = true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouchEvent: STARTED>>>>>>>>>>>")
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        Log.d(TAG, "performClick: STARTED...")
        Log.d(TAG, "performClick: CALLED FROM : ${Exception().getStackTrace()[0].getClassName()} >>> ${Exception().stackTrace[0].methodName}")
        Log.d(TAG, "performClick: CALLED FROM : ${Exception().getStackTrace()[1].getClassName()} >>> ${Exception().stackTrace[1].methodName}")
        Log.d(TAG, "performClick: CALLED FROM : ${Exception().getStackTrace()[2].getClassName()} >>> ${Exception().stackTrace[2].methodName}")
        if (super.performClick()) return true
        Log.d(TAG, "performClick: AFTER IF BLOCK...")

        Log.d(TAG, "performClick: before_fanSpeed = $fanSpeed")
        fanSpeed = fanSpeed.next()
        Log.d(TAG, "performClick: after_fanSpeed = $fanSpeed")

        Log.d(TAG, "performClick: before_contentDescription = $contentDescription")
        contentDescription = resources.getString(fanSpeed.label)
        Log.d(TAG, "performClick: after_contentDescription = $contentDescription")

        Log.d(TAG, "performClick: calling_inValidated......")
        invalidate()
        Log.d(TAG, "performClick: AFTER_inValidated......")
        return true
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        Log.d(TAG, "onSizeChanged: >>>>>>>>>>> width = $width  height=$height o.width = $oldWidth o.height=$oldHeight")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[0].getClassName()} >>> ${Exception().stackTrace[0].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[1].getClassName()} >>> ${Exception().stackTrace[1].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[2].getClassName()} >>> ${Exception().stackTrace[2].methodName}")
        /*
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[3].getClassName()} >>> ${Exception().stackTrace[3].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[4].getClassName()} >>> ${Exception().stackTrace[4].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[5].getClassName()} >>> ${Exception().stackTrace[5].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[6].getClassName()} >>> ${Exception().stackTrace[6].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[7].getClassName()} >>> ${Exception().stackTrace[7].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[8].getClassName()} >>> ${Exception().stackTrace[8].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[9].getClassName()} >>> ${Exception().stackTrace[9].methodName}")
        Log.d(TAG, "onSizeChanged: CALLED FROM : ${Exception().getStackTrace()[10].getClassName()} >>> ${Exception().stackTrace[10].methodName}")*/

        Log.d(TAG, "onSizeChanged: before_radius = $radius")
        radius = (min(width, height) / 2.0 * 0.8).toFloat()
        Log.d(TAG, "onSizeChanged: after_radius = $radius")
    }
    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        Log.d(TAG, "computeXYForSpeed: >>>>>>>> pos = $pos  radius = $radius")
        // Angles are in radians.
        
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        Log.d(TAG, "computeXYForSpeed: startAngle = $startAngle  angle = $angle")


        Log.d(TAG, "computeXYForSpeed: before_XY.... x= $x  y=$y")
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
        Log.d(TAG, "computeXYForSpeed: after_XY.... x= $x  y=$y")
    }
    override fun onDraw(canvas: Canvas) {
        Log.d(TAG, "onDraw: >>>>>>>>>>> canvas = $canvas")
        Log.d(TAG, "onDraw: CALLED FROM : ${Exception().getStackTrace()[0].getClassName()} >>> ${Exception().stackTrace[0].methodName}")
        Log.d(TAG, "onDraw: CALLED FROM : ${Exception().getStackTrace()[1].getClassName()} >>> ${Exception().stackTrace[1].methodName}")
        Log.d(TAG, "onDraw: CALLED FROM : ${Exception().getStackTrace()[2].getClassName()} >>> ${Exception().stackTrace[2].methodName}")
        super.onDraw(canvas)
        // Set dial background color to green if selection not off.
        setBackgroundColor(Color.YELLOW)
        Log.d(TAG, "onDraw: before_paint.color  = $paint")
        paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN
        Log.d(TAG, "onDraw: after_paint.color  = $paint")

        // Draw the dial.
        Log.d(TAG, "\nonDraw: drawing_circle...  x=${(width / 2).toFloat()}  y=${(height / 2).toFloat()} radius=$radius paint=$paint")
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        // Draw the indicator circle.
        Log.d(TAG, "onDraw: drawing_indicator STARTED........")
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        Log.d(TAG, "\nonDraw: radius = $radius   markerRadius = $markerRadius")

        Log.d(TAG, "onDraw: before_pontPosition = $pointPosition")
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        Log.d(TAG, "onDraw: after_pontPosition = $pointPosition")

        Log.d(TAG, "onDraw: before_paint.color = $paint")
        paint.color = Color.BLACK
        Log.d(TAG, "onDraw: after_paint.color = $paint")

        Log.d(TAG, "onDraw: drawing_indicator...  x=${pointPosition.x}  y=${pointPosition.y} radius=${radius/12}  paint=$paint")
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius/12, paint)



        // Draw the text labels.
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        Log.d(TAG, "onDraw: drawing_label STARTED........")
        Log.d(TAG, "onDraw: labelRadius = $labelRadius")
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }
}