package com.mualim.idealbodyapp

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var edtWeight: EditText
    private lateinit var edtHeight: EditText
    private lateinit var btnCount: Button
    private lateinit var tvResult: TextView
    private lateinit var tvNote: TextView
    private lateinit var relativeLayout: RelativeLayout
    private lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWeight = findViewById(R.id.edt_weight)
        edtHeight = findViewById(R.id.edt_height)
        btnCount = findViewById(R.id.btn_count)
        tvResult = findViewById(R.id.tv_result)
        tvNote = findViewById(R.id.tv_note)
        relativeLayout = findViewById(R.id.relativeLayout)
        animationDrawable = relativeLayout.background as AnimationDrawable

        animationDrawable.setEnterFadeDuration(600)
        animationDrawable.setExitFadeDuration(6000)

        btnCount.setOnClickListener {
            val inputWeight = edtWeight.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmpty = false

            when {
                TextUtils.isEmpty(inputWeight) -> {
                    isEmpty = true
                    edtWeight.error = "Kolom ini tidak boleh kosong"
                }
                TextUtils.isEmpty(inputHeight) -> {
                    isEmpty = true
                    edtHeight.error = "Kolom ini tidak boleh kosong"
                }
            }
            if (!isEmpty){
                val weight = inputWeight.toDouble()
                val height = inputHeight.toDouble().div(100).pow(2)


                val idealBody =  weight / height
                tvResult.text = idealBody.toString()

                when {
                    idealBody <= 18 -> {
                        tvNote.text = "Berat badan kurang"
                    }
                    idealBody in 18.0..25.00 -> {
                        tvNote.text = "Berat badan ideal"
                    }
                    idealBody in 25.0..40.0 -> {
                        tvNote.text = "Berat badan berlebih"
                    }
                    else -> {
                        tvNote.text = "Mohon lakukan penanganan secepatnya karena angka ini menunjukkan bahaya."
                    }
                }
            }
        }
    }
    override fun onPause() {
        super.onPause()
        if (animationDrawable.isRunning) {
            animationDrawable.stop()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!animationDrawable.isRunning) {
            animationDrawable.start()
        }
    }
}