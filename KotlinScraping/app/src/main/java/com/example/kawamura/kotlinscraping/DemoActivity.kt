package com.example.kawamura.kotlinscraping

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.TextView
import java.util.*

class DemoActivity : AppCompatActivity() {

    private var mReturnTextView: TextView? = null
    private var mReturnTextViewValue: TextView? = null

    private var mTimer: Timer? = null
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo)

        this.mReturnTextView = findViewById(R.id.textView) as TextView
        this.mReturnTextViewValue = findViewById(R.id.textView1) as TextView
        startAsync()

        this.mTimer = Timer()
        this.mHandler = Handler()
        this.mTimer!!.schedule(object : TimerTask() {
            override fun run() {
                mHandler!!.post(Runnable {
                    // 実行したい処理
                    startAsync()
                })
            }
        }, 1000, 1000) // 実行したい間隔(ミリ秒)
    }

    fun startAsync() {
        val scraping = Scraping(this, this.mReturnTextView!!, this.mReturnTextViewValue!!)
        scraping.execute()
    }

}