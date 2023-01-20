package com.kazim.timerapp

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.kazim.timerapp.databinding.ActivityDetailBinding
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var list:List<User>
    private lateinit var timer:CountDownTimer
    private lateinit var timer2:CountDownTimer

    private  var b:Int ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch{
            list =AppDatabase(this@DetailActivity).userDao().getAllUser()

            var b =0
            while (b<list.size){
                var a =0
                delay(500L)
                while (a<list[b].harekettekrar) {
                    binding.nametext.text=list[b].hareketadi
                    delay(1000L)
                    countDowntimer(list[b].hareketsaniye.toLong())
                    delay((list[b].hareketsaniye+1).toLong()* 1000)
                    countDowntimerr(list[b].hareketmola.toLong())
                    delay((list[b].hareketmola.toLong()+1)*1000)
                    a++
                }
                delay(1000L)
                b++ } } }
    private fun countDowntimer(zaman: Long) {
        timer=object:CountDownTimer(zaman*1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timetext.text=(millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                timer.cancel()
            } }.start() }
    private fun countDowntimerr(zaman: Long) {
        timer2=object:CountDownTimer(zaman*1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timetext.text=(millisUntilFinished/1000).toString()
            }
            override fun onFinish() {
                timer.cancel() } }.start() } }



