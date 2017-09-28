package com.example.kawamura.kotlinscraping

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MarketListAdapter(context: Context, private val mResource: Int, private val mItems: List<MarketListItem>) : ArrayAdapter<MarketListItem>(context, mResource, mItems) {
    private val mInflater: LayoutInflater

    init {
        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View

        if (convertView != null) {
            view = convertView
        } else {
            view = mInflater.inflate(mResource, null)
        }

        // リストビューに表示する要素を取得
        val item = mItems[position]

        // タイトルを設定
        val title = view.findViewById<View>(R.id.title) as TextView
        title.text = item.title
        val content = view.findViewById<View>(R.id.content) as TextView
        content.text = item.content
        val url = view.findViewById<View>(R.id.url) as TextView
        url.text = item.url

        return view
    }
}