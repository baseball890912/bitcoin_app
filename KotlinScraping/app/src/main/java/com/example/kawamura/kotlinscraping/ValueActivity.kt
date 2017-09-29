package com.example.kawamura.kotlinscraping

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.os.AsyncTask
import android.widget.TextView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by kawamura on 2017/09/10.
 */

class ValueActivity(val mParentActivity: Activity,
                    var USDJPYTextView: TextView, var USDJPYTextViewValue: TextView,
                    var EURJPYTextView: TextView, var EURJPYTextViewValue: TextView,
                    var AUDJPYTextView: TextView, var AUDJPYTextViewValue: TextView,
                    var nikkeiTextView: TextView, var nikkeTextViewValue: TextView,
                    var BTCJPYTextView: TextView, var BTCJPYTextViewValue: TextView) : AsyncTask<Void, Void, ValueActivity.GetValue>()  {

    private var mDialog: ProgressDialog? = null

    override fun onPreExecute() {
        mDialog = ProgressDialog(mParentActivity)
        //mDialog!!.setMessage("通信中・・・・・・")
        //mDialog!!.show()
    }



    override fun doInBackground(vararg arg0: Void): GetValue {
        val getvalue = GetValue()
        val values = getvalue.get()
        return values
    }

    override fun onPostExecute(values: GetValue) {
        mDialog!!.dismiss()
        set_text(values.USDJPY!!, this.USDJPYTextView, this.USDJPYTextViewValue)
        set_text(values.EURJPY!!, this.EURJPYTextView, this.EURJPYTextViewValue)
        set_text(values.AUDJPY!!, this.AUDJPYTextView, this.AUDJPYTextViewValue)
        set_text(values.nikkei!!, this.nikkeiTextView, this.nikkeTextViewValue)
        set_text(values.BTCJPY!!, this.BTCJPYTextView, this.BTCJPYTextViewValue)
    }

    class GetValue {
        var USDJPY: String? = null
        var EURJPY: String? = null
        var AUDJPY: String? = null
        var nikkei: String? = null
        var BTCJPY: String? = null

        fun get(): GetValue {
            val list = GetValue()
            var url = "https://info.finance.yahoo.co.jp/fx/"
            var document: Document = Jsoup.connect(url).get()
            list.USDJPY = document.getElementById("USDJPY_top_bid").text()
            list.EURJPY = document.getElementById("EURJPY_top_bid").text()
            list.AUDJPY = document.getElementById("AUDJPY_top_bid").text()

            url = "https://stocks.finance.yahoo.co.jp/stocks/detail/?code=998407.O"
            document = Jsoup.connect(url).get()
            var nikkei = document.getElementsByClass("stoksPrice").text()
            list.nikkei = nikkei.replace(",", "")

            url = "https://cc.minkabu.jp/pair/BTC_JPY"
            document = Jsoup.connect(url).get()
            var BTCJPY = document.getElementById("btc_jpy_top_bid").text()
            list.BTCJPY = BTCJPY.replace(",", "")

            return list
        }
    }

    fun set_text(string: String, mTextView: TextView, mTextViewValue: TextView){
        if (mTextView.text == "TextView"){
            mTextView.text = string
            mTextViewValue.text = "---"
            mTextViewValue.setTextColor(Color.BLACK)
        }
        else{
            val value0: Float = (mTextView.text as String).toFloat()
            val value1: Float = string.toFloat()

            mTextView.text = string

            if (value0 > value1){
                mTextView.setTextColor(Color.BLUE)
                mTextViewValue.text = "↓"
                mTextViewValue.setTextColor(Color.BLUE)
            }
            else if(value0 < value1){
                mTextView.setTextColor(Color.RED)
                mTextViewValue.text = "↑"
                mTextViewValue.setTextColor(Color.RED)
            }
            else{
                mTextView.setTextColor(Color.BLACK)
                mTextViewValue.text = "---"
                mTextViewValue.setTextColor(Color.BLACK)
            }
        }
    }
}