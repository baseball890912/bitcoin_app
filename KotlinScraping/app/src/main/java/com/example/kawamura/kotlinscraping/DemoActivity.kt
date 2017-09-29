package com.example.kawamura.kotlinscraping

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.TextView
import java.util.*
import android.R.string.cancel



class DemoActivity : AppCompatActivity() {

    private var USDJPYTextView: TextView? = null
    private var USDJPYTextViewValue: TextView? = null
    private var EURJPYTextView: TextView? = null
    private var EURJPYTextViewValue: TextView? = null
    private var AUDJPYTextView: TextView? = null
    private var AUDJPYTextViewValue: TextView? = null
    private var nikkeiTextView: TextView? = null
    private var nikkeiTextViewValue: TextView? = null
    private var BTCJPYTextView: TextView? = null
    private var BTCJPYTextViewValue: TextView? = null

    private var mTimer: Timer? = null
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo)

        this.USDJPYTextView = findViewById(R.id.USD_JPY) as TextView
        this.USDJPYTextViewValue = findViewById(R.id.USD_JPY_value) as TextView
        this.EURJPYTextView = findViewById(R.id.EUR_JPY) as TextView
        this.EURJPYTextViewValue = findViewById(R.id.EUR_JPY_value) as TextView
        this.AUDJPYTextView = findViewById(R.id.AUD_JPY) as TextView
        this.AUDJPYTextViewValue = findViewById(R.id.AUD_JPY_value) as TextView
        this.nikkeiTextView = findViewById(R.id.nikkei) as TextView
        this.nikkeiTextViewValue = findViewById(R.id.nikkei_value) as TextView
        this.BTCJPYTextView = findViewById(R.id.BTCJPY) as TextView
        this.BTCJPYTextViewValue = findViewById(R.id.BTCJPY_value) as TextView

        this.mTimer = Timer()
        this.mHandler = Handler()
        this.mTimer!!.schedule(object : TimerTask() {
            override fun run() {
                mHandler!!.post(Runnable {
                    // 実行したい処理
                    startAsync()
                })
            }
        }, 1500, 1500) // 実行したい間隔(ミリ秒)
    }

    fun startAsync() {
        val scraping = Scraping(this,
                this.USDJPYTextView!!, this.USDJPYTextViewValue!!,
                this.EURJPYTextView!!, this.EURJPYTextViewValue!!,
                this.AUDJPYTextView!!, this.AUDJPYTextViewValue!!,
                this.nikkeiTextView!!, this.nikkeiTextViewValue!!,
                this.BTCJPYTextView!!, this.BTCJPYTextViewValue!!)
        scraping.execute()
    }

    override fun onPause() {
        super.onPause()

        if (this.mTimer != null) {
            this.mTimer!!.cancel()
            this.mTimer = null
        }
    }

}