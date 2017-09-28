package com.example.kawamura.kotlinscraping

/**
 * Created by kawamura on 2017/09/27.
 */

class NewsListItem {
    var title: String? = null
    var date: String? = null
    var content: String? = null
    var url: String? = null
    constructor(title: String, date: String, content: String, url: String) {
        this.title = title
        this.date = date
        this.content = content
        this.url = url
    }
    fun setmTitle(title: String) {
        this.title = title
    }
}

