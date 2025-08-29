package com.sc3039129.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sc3039129.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentValue: String=""
    private var latestValue: Double=0.0
    private var operation: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculo = binding.calculo
        val result = binding.result

        fun calculate(){
            val currentValueDouble: Double = currentValue.toDouble()
            val resultCalculo = when (this.operation) {
                "+" -> latestValue + currentValueDouble
                "-" -> latestValue - currentValueDouble
                "*" -> latestValue * currentValueDouble
                "/" -> {
                    if (currentValueDouble != 0.0) {
                        latestValue / currentValueDouble
                    } else {
                        result.text = "Não é possível dividir por zero"
                        return
                    }
                }
                else -> 0.0
            }

            result.text=resultCalculo.toString()
        }

        fun addNumberToCalculo(number:String){
            currentValue+= number
            calculo.text= "${calculo.text}${number}"
            if(this.operation != null){ calculate() }
        }

        binding.um.setOnClickListener{addNumberToCalculo("1")}
        binding.dois.setOnClickListener{addNumberToCalculo("2")}
        binding.tres.setOnClickListener{ addNumberToCalculo("3") }
        binding.quatro.setOnClickListener{ addNumberToCalculo("4") }
        binding.cinco.setOnClickListener{ addNumberToCalculo("5") }
        binding.seis.setOnClickListener{ addNumberToCalculo("6") }
        binding.sete.setOnClickListener{ addNumberToCalculo("7") }
        binding.oito.setOnClickListener{ addNumberToCalculo("8") }
        binding.nove.setOnClickListener{ addNumberToCalculo("9") }
        binding.zero.setOnClickListener{ addNumberToCalculo("0") }

        @SuppressLint("SetTextI18n")
        fun addOperationToCalculo(op:String){
            this.operation= op
            this.latestValue+= currentValue.toDouble()
            this.currentValue=""
            calculo.text= "${calculo.text}${operation}"
        }

        binding.adicao.setOnClickListener {addOperationToCalculo("+")}
        binding.subtracao.setOnClickListener {addOperationToCalculo("-")}
        binding.divisao.setOnClickListener {addOperationToCalculo("/")}
        binding.multiplicacao.setOnClickListener {addOperationToCalculo("*")}

        binding.ce.setOnClickListener {
            currentValue=""
            latestValue=0.0
            calculo.text = ""
            result.text=""
        }

        binding.igual.setOnClickListener { calculate() }
    }
}