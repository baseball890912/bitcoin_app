package com.example.kawamura.kotlinscraping

import android.app.ProgressDialog
import android.os.AsyncTask
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

/**
 * Created by kawamura on 2017/09/27.
 */
class MarketListTask(private val mParentActivity: MarketListActivity) : AsyncTask<Int, Int, ArrayList<MarketListItem>>() {

    private var mDialog: ProgressDialog? = null

    override fun onPreExecute() {
        mDialog = ProgressDialog(mParentActivity)
        mDialog!!.setMessage("取得中・・・・・・")
        mDialog!!.show()
    }

    // 非同期処理
    override fun doInBackground(vararg p0: Int?):  ArrayList<MarketListItem> {
        //var result = ArrayList<String>()
        var result =  ArrayList<MarketListItem>()
        try {
            val url = "http://btcnews.jp/category/market/"
            val document: Document = Jsoup.connect(url).get()

            val aTag: Elements = document.getElementsByTag("a")

            val content = document.select("div.entry-content")
            val content_list = ArrayList<String>()
            content.forEach {
                content_list.add(it.text())
            }
            //Log.d("debug", content_list.toString())
            var index = 0
            for (element in aTag!!) {
                if (element.toString().contains("alt=\"相場\"") && !element.toString().contains("height=\"50\"")) {
                    val url = element.attr("href")
                    var title = element.attr("title")
                    result.add(MarketListItem(title, content_list[index], url))
                    index++
                }
            }
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            //return "ERROR"
        }
        return result
    }

    // 非同期処理が終了後、結果をメインスレッドに返す
    override fun onPostExecute(result:  ArrayList<MarketListItem>?) {
        mDialog!!.dismiss()
        mParentActivity.setListView(result)
    }
}