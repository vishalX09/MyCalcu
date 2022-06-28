package silver.struggle.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput :TextView? = null
    var lastnumeric : Boolean =  false;
    var lastdot : Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput);
    }

    fun onDigit(view : View){
        tvInput?.append((view as Button).text)
        lastnumeric = true
        lastdot = false

    }
    fun onclear(view : View){
        tvInput?.text = "";
    }

    fun onDecimalPoint (view : View){
        if(lastnumeric && !lastdot){
            tvInput?.append(".")
            lastnumeric = false
            lastdot = true;

        }
    }
    fun onopreator(view : View){
        tvInput?.text?.let {
            if(lastnumeric && !isopreatoradded(it.toString())){
                tvInput?.append((view as Button).text)
                lastdot = false
                lastnumeric = false
            }
        }
    }

    fun onequal(view : View){
        if(lastnumeric){
            var tvvalue = tvInput?.text.toString()
            var prefix = ""
            try {
                if(tvvalue.startsWith("-")){
                    prefix = "-"
                    tvvalue = tvvalue.substring(1)

                }
                if(tvvalue.contains("-")) {
                    val splitvalue = tvvalue.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removezeroAfterdot((one.toDouble() - two.toDouble()).toString())
                }else  if(tvvalue.contains("+")) {
                    val splitvalue = tvvalue.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removezeroAfterdot((one.toDouble() + two.toDouble()).toString())
                }else  if(tvvalue.contains("/")) {
                    val splitvalue = tvvalue.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removezeroAfterdot((one.toDouble() / two.toDouble()).toString())
                }else  if(tvvalue.contains("*")) {
                    val splitvalue = tvvalue.split("*")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = removezeroAfterdot((one.toDouble() * two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removezeroAfterdot(result : String): String {
        var value = result
        if(result.contains("0"))
            value = result.substring(0 , result.length-2)

        return value

    }
    private fun isopreatoradded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")||
                    value.contains("*")||
                    value.contains("+")||
                    value.contains("-");
        }
    }

}