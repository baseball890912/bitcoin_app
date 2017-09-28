package com.example.kawamura.kotlinscraping

/**
 * Created by kawamura on 2017/09/27.
 */
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class NewsListAdapter(context: Context, private val mResource: Int, private val mItems: List<NewsListItem>) : ArrayAdapter<NewsListItem>(context, mResource, mItems) {
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
        val date = view.findViewById<View>(R.id.date) as TextView
        date.text = item.date
        val content = view.findViewById<View>(R.id.content) as TextView
        content.text = item.content
        val url = view.findViewById<View>(R.id.url) as TextView
        url.text = item.url

        return view
    }
}