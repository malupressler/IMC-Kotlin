package com.example.appimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso: AppCompatEditText
    private lateinit var etAltura: AppCompatEditText
    private lateinit var btnCalcular: AppCompatButton
    private lateinit var tvResult: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnCalcular.setOnClickListener {
            calculateImc()
        }
        tvResult = findViewById(R.id.tvResult)
    }

    private fun calcIMC(Peso: String, Altura: String): Double {
        return Peso.toDouble() / (Altura.toDouble() * Altura.toDouble())
    }

    private fun checkIMC(db: Double): String {
        return when (db) {
            in 17.1..18.49 -> "Abaixo do peso."
            in 18.5..24.99 -> "Peso normal."
            in 25.0..29.99 -> "Acima do peso."
            in 30.0..34.99 -> "Obesidade I."
            in 35.0..39.99 -> "Obesidade II(severa)."
            else -> "Obesidade III(mórbida)."
        }
    }

    private fun calculateImc() {
        if (etPeso.text.isNullOrEmpty() || etAltura.text.isNullOrEmpty()) {
            Snackbar.make(tvResult, "Por favor preencha todos os dados!", Snackbar.LENGTH_LONG)
                .show()
        } else {
            val imc = calcIMC(etPeso.text.toString(), etAltura.text.toString())
            val alturad = DecimalFormat("#.00")
            val imcResp = "IMC: " + alturad.format(imc) + "\n" + checkIMC(imc)
            tvResult.text = imcResp
            tvResult.visibility = View.VISIBLE
            etPeso.text!!.clear()
            etAltura.text!!.clear()
        }
    }
}


