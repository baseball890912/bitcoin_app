package com.example.kawamura.kotlinscraping

/**
 * Created by kawamura on 2017/09/27.
 */
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

import java.util.ArrayList

class NewsListActivity : AppCompatActivity() {
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customize_news_main)
        // レイアウトからリストビューを取得
        val listView = findViewById(R.id.sample_listview) as ListView
        // リストビューに表示する要素を設定
        val listItems = ArrayList<NewsListItem>()
        for (i in 0..2) {
            val item = NewsListItem("title", "2017/09/27", "content", "http://btcnews.jp/category/market/")
            listItems.add(item)
        }
        // 出力結果をリストビューに表示
        val adapter = NewsListAdapter(this, R.layout.customize_news_listview, listItems)
        listView.adapter = adapter
    }
    */
    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.customize_news_main)
        // レイアウトからリストビューを取得
        this.listView = findViewById(R.id.customize_listview) as ListView
        // リストビューに表示する要素を設定
        // 出力結果をリストビューに表示

        startAsync()

    }

    fun startAsync() {
        val testTask = NewsListTask(this)
        // executeを呼んでAsyncTaskを実行する、パラメータは１番目
        testTask.execute()
    }

    fun setListView(elements: ArrayList<NewsListItem>?) {
        var adapter = NewsListAdapter(this, R.layout.customize_news_listview_new, elements!!)
        this.listView!!.adapter = adapter
    }

}