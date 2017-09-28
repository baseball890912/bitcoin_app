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
class NewsListTask(private val mParentActivity: NewsListActivity) : AsyncTask<Int, Int, ArrayList<NewsListItem>>() {

    private var mDialog: ProgressDialog? = null

    override fun onPreExecute() {
        mDialog = ProgressDialog(mParentActivity)
        mDialog!!.setMessage("取得中・・・・・・")
        mDialog!!.show()
    }

    // 非同期処理
    override fun doInBackground(vararg p0: Int?):  ArrayList<NewsListItem> {
        //var result = ArrayList<String>()
        var result =  ArrayList<NewsListItem>()
        try {
            val url = "http://btcnews.jp/category/news/"
            val document: Document = Jsoup.connect(url).get()

            val aTag: Elements = document.getElementsByTag("a")

            val date = document.select("span.entry-date")
            val date_list = ArrayList<String>()
            date.forEach {
                date_list.add(it.text())
            }

            val content = document.select("div.entry-content")
            val content_list = ArrayList<String>()
            content.forEach {
                content_list.add(it.text())
            }
            //Log.d("debug", content_list.toString())
            var index = 0
            for (element in aTag!!) {
                if (element.toString().contains("alt=\"ニュース\"") && !element.toString().contains("height=\"50\"")) {
                    val url = element.attr("href")
                    var title = element.attr("title")
                    result.add(NewsListItem(title, date_list[index], content_list[index], url))
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

    // 途中経過をメインスレッドに返す
    //protected override fun onProgressUpdate(vararg values: Int?) {
    //progressDialog_.incrementProgressBy(progress[0]);
    //mParentActivity.setTextView(values[0])
    //}

    // 非同期処理が終了後、結果をメインスレッドに返す
    override fun onPostExecute(result:  ArrayList<NewsListItem>?) {
        mDialog!!.dismiss()
        mParentActivity.setListView(result)
    }
}