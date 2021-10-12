package com.example.kisshtproject.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.kisshtproject.R
import com.example.kisshtproject.model.Shape
import java.util.*
import kotlin.random.Random

const val SHAPE_VIEW_CIRCLE = 1
const val SHAPE_VIEW_SQUARE = 2
private const val radius = 50f

class ShapeView: View {

    private var circlePaint = Paint()
    private var squarePaint = Paint()
    private var mCanvas: Canvas? = null
    private var shapeStack = Stack<Shape>()

    constructor(context: Context): super(context) {

    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        //initAttributeSet(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        //initAttributeSet(attrs)
    }

    init {
        circlePaint.color = ContextCompat.getColor(getContext(), R.color.purple_500)
        circlePaint.style = Paint.Style.FILL

        squarePaint.color = ContextCompat.getColor(getContext(), R.color.purple_200)
        squarePaint.style = Paint.Style.FILL
    }

    /*private fun initAttributeSet(attrs: AttributeSet) {

    }*/

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mCanvas = canvas
        val iterator = shapeStack.iterator()
        while (iterator.hasNext()) {
            val shapeObj = iterator.next()
            if (shapeObj.type == Shape.SHAPE_CIRCLE) {
                mCanvas?.drawCircle(shapeObj.xPos1!!.toFloat(), shapeObj.yPos1!!.toFloat(), radius, circlePaint)
            } else if (shapeObj.type == Shape.SHAPE_SQUARE) {
                mCanvas?.drawRect(shapeObj.xPos1!!.toFloat(), shapeObj.yPos1!!.toFloat(),
                        shapeObj.xPos2!!.toFloat(), shapeObj.yPos2!!.toFloat(), squarePaint)
            }
        }
    }

    fun addShape(type: Int) {
        when(type) {
            SHAPE_VIEW_CIRCLE -> {
                val shapeObj = Shape()
                shapeObj.type = Shape.SHAPE_CIRCLE
                shapeObj.xPos1 = getXRandom()
                shapeObj.yPos1 = getYRandom()
                shapeStack.push(shapeObj)

                invalidate()
            }

            SHAPE_VIEW_SQUARE -> {
                val shapeObj = Shape()
                shapeObj.type = Shape.SHAPE_SQUARE
                shapeObj.xPos1 = getXRandom()
                shapeObj.yPos1 = getYRandom()
                shapeObj.xPos2 = shapeObj.xPos1!! + radius.toInt()
                shapeObj.yPos2 = shapeObj.yPos1!! + radius.toInt()
                shapeStack.push(shapeObj)

                invalidate()
            }
        }
    }

    fun undo() {
        if (shapeStack.isNotEmpty()) {
            shapeStack.pop()
            invalidate()
        }
    }

    private fun getXRandom(): Int {
        val xmin = 0
        val xmax = width
        return Random.nextInt(xmax - xmin + 1) + xmin
    }

    private fun getYRandom(): Int {
        val ymin = 0
        val ymax = height
        return Random.nextInt(ymax - ymin + 1) + ymin
    }

}