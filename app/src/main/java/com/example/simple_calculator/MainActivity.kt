package com.example.simple_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.example.simple_calculator.databinding.ActivityMainBinding
import org.mozilla.javascript.Context



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private fun getResult(data: String): String{
        try {
            val context = Context.enter()
            context.optimizationLevel = -1
            val scriptable = context.initStandardObjects()
            var finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString()
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","")
            }
            return finalResult
        }catch (e: Exception){
            return "err"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result_txt: TextView = findViewById(R.id.result_txt)
        val solution_txt: TextView = findViewById(R.id.solution_txt)
        val button_c: MaterialButton = findViewById(R.id.button_c)
        val button_open_bracket: MaterialButton = findViewById(R.id.button_open_bracket)
        val button_close_bracket: MaterialButton = findViewById(R.id.button_close_bracket)
        val button_divide: MaterialButton = findViewById(R.id.button_divide)
        val button_nine: MaterialButton = findViewById(R.id.button_nine)
        val button_eight: MaterialButton = findViewById(R.id.button_eight)
        val button_seven: MaterialButton = findViewById(R.id.button_seven)
        val button_multiply: MaterialButton = findViewById(R.id.button_multiply)
        val button_six: MaterialButton = findViewById(R.id.button_six)
        val button_five: MaterialButton = findViewById(R.id.button_five)
        val button_four: MaterialButton = findViewById(R.id.button_four)
        val button_sub: MaterialButton = findViewById(R.id.button_sub)
        val button_three: MaterialButton = findViewById(R.id.button_three)
        val button_two: MaterialButton = findViewById(R.id.button_two)
        val button_one: MaterialButton = findViewById(R.id.button_one)
        val button_add: MaterialButton = findViewById(R.id.button_add)
        val button_dot: MaterialButton = findViewById(R.id.button_dot)
        val button_equal: MaterialButton = findViewById(R.id.button_equal)
        val button_ac: MaterialButton = findViewById(R.id.button_ac)
        val button_zero: MaterialButton = findViewById(R.id.button_zero)


        val buttonClickListener = View.OnClickListener { view ->
            if (view is MaterialButton) {
                val buttonText = view.text.toString()
                var currentText = solution_txt.text.toString()
                if(buttonText.equals("A")){
                    solution_txt.text=""
                    result_txt.text="0"
                    return@OnClickListener
                }else if(buttonText.equals("=")){
                    solution_txt.text=result_txt.text
                    return@OnClickListener
                }else if(buttonText.equals("C")){
                    currentText = currentText.substring(0,currentText.length-1)

                }else {
                    currentText = currentText + buttonText
                }
                solution_txt.text=currentText
                var finalResult = getResult(currentText)
                if (!finalResult.equals("err")){
                    result_txt.text=finalResult
                }
            }
        }

        for (button in listOf(binding.buttonZero, binding.buttonOne, binding.buttonTwo, binding.buttonThree, binding.buttonFour, binding.buttonFive, binding.buttonSix, binding.buttonSeven, binding.buttonEight, binding.buttonNine, binding.buttonCloseBracket, binding.buttonOpenBracket, binding.buttonC, binding.buttonAc, binding.buttonDivide, binding.buttonDot, binding.buttonAdd, binding.buttonEqual, binding.buttonMultiply, binding.buttonSub)) {
            button.setOnClickListener(buttonClickListener)
        }
    }

    }
