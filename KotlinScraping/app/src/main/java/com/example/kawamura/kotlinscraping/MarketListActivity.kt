package com.example.kawamura.kotlinscraping

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import java.util.ArrayList

class MarketListActivity : AppCompatActivity() {

    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.customize_market_main)
        // レイアウトからリストビューを取得
        this.listView = findViewById(R.id.customize_listview) as ListView
        // リストビューに表示する要素を設定
        // 出力結果をリストビューに表示

        startAsync()

    }

    fun startAsync() {
        val testTask = MarketListTask(this)
        // executeを呼んでAsyncTaskを実行する、パラメータは１番目
        testTask.execute()
    }

    fun setListView(elements: ArrayList<MarketListItem>?) {
        var adapter = MarketListAdapter(this, R.layout.customize_market_listview_new, elements!!)
        this.listView!!.adapter = adapter
    }

}