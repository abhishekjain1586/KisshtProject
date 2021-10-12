package com.example.kisshtproject.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kisshtproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.title_launcher)

        initListeners()
    }

    private fun initListeners() {
        btnTaskOne.setOnClickListener(this)
        btnTaskTwo.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnTaskOne -> {
                performTaskOne()
            }

            R.id.btnTaskTwo -> {
                performTaskTwo()
            }
        }
    }

    private fun performTaskOne() {
        val intent = Intent(this, TaskOneActivity::class.java)
        startActivity(intent)
    }

    private fun performTaskTwo() {
        val intent = Intent(this, TaskTwoActivity::class.java)
        startActivity(intent)
    }
}