package com.example.kawamura.kotlinscraping

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import java.util.*


class MainActivity : AppCompatActivity() /*, View.OnClickListener*/ {
    //private var mReturnTextView: TextView? = null

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
        setContentView(R.layout.activity_main)

        //val button = findViewById(R.id.button) as Button
        //button.setOnClickListener(this)

        //mReturnTextView = findViewById(R.id.textView1) as TextView

        //val graph = findViewById(R.id.graph) as Button
        //graph.setOnClickListener(this)
        this.USDJPYTextView = findViewById(R.id.USDJPY) as TextView
        this.USDJPYTextViewValue = findViewById(R.id.USDJPY_value) as TextView
        this.EURJPYTextView = findViewById(R.id.EURJPY) as TextView
        this.EURJPYTextViewValue = findViewById(R.id.EURJPY_value) as TextView
        this.AUDJPYTextView = findViewById(R.id.AUDJPY) as TextView
        this.AUDJPYTextViewValue = findViewById(R.id.AUDJPY_value) as TextView
        this.nikkeiTextView = findViewById(R.id.nikkei) as TextView
        this.nikkeiTextViewValue = findViewById(R.id.nikkei_value) as TextView
        this.BTCJPYTextView = findViewById(R.id.BTCJPY) as TextView
        this.BTCJPYTextViewValue = findViewById(R.id.BTCJPY_value) as TextView
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
        }, 1500, 1500) // 実行したい間隔(ミリ秒)
    }

    fun startAsync() {
        val valueactivity = ValueActivity(this,
                this.USDJPYTextView!!, this.USDJPYTextViewValue!!,
                this.EURJPYTextView!!, this.EURJPYTextViewValue!!,
                this.AUDJPYTextView!!, this.AUDJPYTextViewValue!!,
                this.nikkeiTextView!!, this.nikkeiTextViewValue!!,
                this.BTCJPYTextView!!, this.BTCJPYTextViewValue!!)
        valueactivity.execute()
    }

    override fun onPause() {
        super.onPause()

        if (this.mTimer != null) {
            this.mTimer!!.cancel()
            this.mTimer = null
        }
    }

    /*
    override fun onClick(v: View) {
        if (v.id === R.id.button) {
            val scraping = Scraping(this,mReturnTextView!!)
            scraping.execute()
        }
        else if (v.id === R.id.graph){
            val intent = Intent(application, GraphLineActivity::class.java)
            startActivity(intent)
        }
    }
    */


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //main.xmlの内容を読み込む
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.item1 -> {
                val intent = Intent(application, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.item2 -> {
                val intent = Intent(application, GraphLineActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.item3 -> {
                val intent = Intent(application, NewsListActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.item4 -> {
                val intent = Intent(application, MarketListActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.item5 -> {
                val intent = Intent(application, DemoActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
