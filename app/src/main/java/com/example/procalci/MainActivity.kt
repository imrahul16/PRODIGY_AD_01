package com.example.procalci

import android.os.Bundle
import android.view.Display
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(){
    private lateinit var display: TextView
    private var expression = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById<TextView>(R.id.display)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnMultiply, R.id.btnMinus, R.id.btnPlus, R.id.btnDivide, R.id.btnpoint
        )
        for (id in buttons){
            findViewById<Button>(id).setOnClickListener {
                val value = (it as Button).text
                expression += value
                display.text = expression
            }
        }
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            expression=""
            display.text="0"
        }
        findViewById<Button>(R.id.btnBackSpace).setOnClickListener {
            if (expression.isNotEmpty()){
                expression=expression.dropLast(1)
                display.text=if (expression.isEmpty())"0" else expression
            }
        }
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            try{
                val result= eval(expression)
                display.text = result.toString()
                expression = result.toString()
            }
            catch (e: Exception){
                display.text ="Error"
                expression=""
            }
        }
    }
    private fun eval(expression: String):Double{
        return ExpressionBuilder(expression).build().evaluate()
    }
}