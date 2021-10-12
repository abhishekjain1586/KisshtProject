package com.example.kisshtproject.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kisshtproject.R
import com.example.kisshtproject.view.custom.SHAPE_VIEW_CIRCLE
import com.example.kisshtproject.view.custom.SHAPE_VIEW_SQUARE
import kotlinx.android.synthetic.main.activity_task_one.*

class TaskOneActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_one)
        title = getString(R.string.title_draw_shape)

        initListeners()
    }

    private fun initListeners() {
        btnCircle.setOnClickListener(this)
        btnSquare.setOnClickListener(this)
        btnUndo.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnCircle -> {
                drawCircle()
            }

            R.id.btnSquare -> {
                drawSquare()
            }

            R.id.btnUndo -> {
                undoShape()
            }
        }
    }

    private fun drawCircle() {
        svDraw.addShape(SHAPE_VIEW_CIRCLE)
    }

    private fun drawSquare() {
        svDraw.addShape(SHAPE_VIEW_SQUARE)
    }

    private fun undoShape() {
        svDraw.undo()
    }
}