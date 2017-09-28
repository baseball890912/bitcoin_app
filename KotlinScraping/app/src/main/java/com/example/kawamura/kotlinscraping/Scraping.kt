package com.example.kawamura.kotlinscraping

import android.app.Activity
import android.app.ProgressDialog
import android.widget.TextView
import android.os.AsyncTask
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL




/**
 * Created by kawamura on 2017/09/10.
 */

class Scraping(val mParentActivity: Activity, var mTextView: TextView, var mTextViewValue: TextView) : AsyncTask<Void, Void, String>()  {

    private var mDialog: ProgressDialog? = null

    override fun onPreExecute() {
        mDialog = ProgressDialog(mParentActivity)
        mDialog!!.setMessage("通信中・・・・・・")
        mDialog!!.show()
    }

    override fun doInBackground(vararg arg0: Void): String {
        return exec_get()
    }

    override fun onPostExecute(string: String) {
        mDialog!!.dismiss()
        if (this.mTextView.text == "TextView"){
            this.mTextView.text = string
            this.mTextViewValue.text = "---"
        }
        else{
            Log.d("debug", this.mTextView.text as String?) // TextView
            this.mTextView.text = string
            this.mTextViewValue.text = "↑"
            Log.d("debug", this.mTextView.text as String?) // 112.749
        }
    }

    fun exec_get(): String {
        try {
            val url = "https://info.finance.yahoo.co.jp/fx/"
            val document: Document = Jsoup.connect(url).get()

            //val aTag: Elements = document.getElementsByTag("a")
            val USDJPY = document.getElementById("USDJPY_top_bid")
            //Log.d("debug","[fuga.text()]\r\n" + USDJPY.text() + "\r\n")

            val EURJPY = document.getElementById("EURJPY_top_bid")
            //Log.d("debug","[fuga.text()]\r\n" + EURJPY.text() + "\r\n")

            val AUDJPY = document.getElementById("AUDJPY_top_bid")
            //Log.d("debug","[fuga.text()]\r\n" + AUDJPY.text() + "\r\n")

            return USDJPY.text()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            return "ERROR"
        }
    }

}
