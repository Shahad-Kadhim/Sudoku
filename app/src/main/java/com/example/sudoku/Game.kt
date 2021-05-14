package com.example.sudoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.sudoku.databinding.ActivityGameBinding

class Game : AppCompatActivity() {
    val field= arrayOfNulls<Array<TextView?>>(9)
    lateinit var binding :ActivityGameBinding
    var viewPressed:TextView?=null
    var counter=10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            field.apply{
                for( i in 0.until(9)){
                    this[i]= arrayOfNulls(9)
                }
                initialArray()
            }
        }
        callback()

    }

    private fun callback() {
        binding.delete.setOnClickListener {
            if (viewPressed!!.text.isNotEmpty()) counter++
            viewPressed!!.text = ""
            if (counter > 0) binding.playComputer.isEnabled = false
        }
    }
    private fun initialArray(){
            binding.run {
                field.apply {
                    this[0]!!.apply {
                        this[0] = r1c1
                        this[1] = r1c2
                        this[2] = r1c3
                        this[3] = r1c4
                        this[4] = r1c5
                        this[5] = r1c6
                        this[6] = r1c7
                        this[7] = r1c8
                        this[8] = r1c9
                    }
                    this[1]!!.apply {
                        this[0] = r2c1
                        this[1] = r2c2
                        this[2] = r2c3
                        this[3] = r2c4
                        this[4] = r2c5
                        this[5] = r2c6
                        this[6] = r2c7
                        this[7] = r2c8
                        this[8] = r2c9
                    }
                    this[2]!!.apply {
                        this[0] = r3c1
                        this[1] = r3c2
                        this[2] = r3c3
                        this[3] = r3c4
                        this[4] = r3c5
                        this[5] = r3c6
                        this[6] = r3c7
                        this[7] = r3c8
                        this[8] = r3c9
                    }
                    this[3]!!.apply {
                        this[0] = r4c1
                        this[1] = r4c2
                        this[2] = r4c3
                        this[3] = r4c4
                        this[4] = r4c5
                        this[5] = r4c6
                        this[6] = r4c7
                        this[7] = r4c8
                        this[8] = r4c9
                    }
                    this[4]!!.apply {
                        this[0] = r5c1
                        this[1] = r5c2
                        this[2] = r5c3
                        this[3] = r5c4
                        this[4] = r5c5
                        this[5] = r5c6
                        this[6] = r5c7
                        this[7] = r5c8
                        this[8] = r5c9
                    }
                    this[5]!!.apply {
                        this[0] = r6c1
                        this[1] = r6c2
                        this[2] = r6c3
                        this[3] = r6c4
                        this[4] = r6c5
                        this[5] = r6c6
                        this[6] = r6c7
                        this[7] = r6c8
                        this[8] = r6c9
                    }
                    this[6]!!.apply {
                        this[0] = r7c1
                        this[1] = r7c2
                        this[2] = r7c3
                        this[3] = r7c4
                        this[4] = r7c5
                        this[5] = r7c6
                        this[6] = r7c7
                        this[7] = r7c8
                        this[8] = r7c9
                    }
                    this[7]!!.apply {
                        this[0] = r8c1
                        this[1] = r8c2
                        this[2] = r8c3
                        this[3] = r8c4
                        this[4] = r8c5
                        this[5] = r8c6
                        this[6] = r8c7
                        this[7] = r8c8
                        this[8] = r8c9
                    }
                    this[8]!!.apply {
                        this[0] = r9c1
                        this[1] = r9c2
                        this[2] = r9c3
                        this[3] = r9c4
                        this[4] = r9c5
                        this[5] = r9c6
                        this[6] = r9c7
                        this[7] = r9c8
                        this[8] = r9c9
                    }
                }
            }
        }

    fun click(v:View){
        if(v!=viewPressed){
            viewPressed=v as TextView
            if(counter>0) setEnableNumbers(true,checkEnable(viewPressed as View))
            binding.delete.isEnabled=true
            v.setBackgroundResource(R.color.pressed)
        }
        else{
            viewPressed=null
            takeIf { (v as TextView).text.isEmpty() }?.let {  v.setBackgroundResource(R.color.field_baground)}
            setEnableNumbers(false)
        }
    }
    fun setNumber(v:View){
        viewPressed!!.apply {
            text = (v as TextView).text.toString()
            Log.i("MM",this.tag as String)
            setBackgroundResource(R.color.taken)
        }
            counter--
            if(counter==0) binding.playComputer.isEnabled=true
        }

    private fun setEnableNumbers(enable:Boolean,listDisable :MutableSet<String> = mutableSetOf()) {
        binding.run{
            numTwo.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            numOne.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            numThree.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            numFour.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            numFive.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            numSix.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            numSeven.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            numEight.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            numNine.apply {
                isEnabled = if (this.text.toString() in listDisable) false else enable
            }
            delete.isEnabled=enable
        }
    }
    private  fun checkEnable(v:View):MutableSet<String>{
        val row = (v.tag as String)[0].toString().toInt()
        val col =(v.tag as String)[1].toString().toInt()
        var p=mutableListOf<String>()
        field[row]!!.filter { it!!.text.toString().isNotEmpty() }.all { p.add(it!!.text.toString())}
        mutableListOf(field[0]!![col]!!,field[1]!![col]!!,field[2]!![col]!!,field[3]!![col]!!,field[4]!![col]!!,field[5]!![col]!!,field[6]!![col]!!,field[7]!![col]!!,field[8]!![col]!!).filter { it.text.toString().isNotEmpty() }.all { p.add(it.text.toString()) }
        val rowRange =when (row){
            in 0..2 -> 0..2
            in 3..5 ->3..5
            else -> 6..8
        }
        val colRange =when (col){
            in 0..2 -> 0..2
            in 3..5 ->3..5
            else -> 6..8
        }
        for (row in rowRange)
            for (col in colRange)
                if(field[row]!![col]!!.text.toString().isNotEmpty()) p.add(field[row]!![col]!!.text.toString())
        return p.toMutableSet()
    }
    fun playComputer(v:View){
        field.flatMap { it!!.toList() }.filter { it!!.text.toString().isEmpty()}.forEach {
            val m=checkEnable(it!!)
            for( c in 0..100) {
                val i = (1..9).random().toString()
                if (i !in m) {
                    it.text=i
                    break
                }
            }
        }
        binding.grid.run {
            isEnabled = false
            alpha=0.2f
        }
        v.visibility=View.INVISIBLE
        binding.playAgain.visibility=View.VISIBLE
        binding.resualt.text= if(field.flatMap { it!!.toList() }.filter { it!!.text.toString().isEmpty()}.size==0)"WIN!!" else "FAIL!!"
            binding.resualt.visibility=View.VISIBLE
        }
    fun hide(v:View){
        v.visibility=View.INVISIBLE
        binding.grid.run {
            alpha=1.0f
        }
        binding.playAgain.visibility=View.VISIBLE
        binding.playAgain.isEnabled=true
    }
    fun playAgain(v:View){
        binding= ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            field.apply{
                for( i in 0.until(9)){
                    this[i]= arrayOfNulls(9)
                }
            }
            initialArray()
        }
        counter=10
    }
}




//
//    fun goToHome(v: View){
//        startActivity(Intent(this,MainActivity::class.java))
//        finish()
//    }

