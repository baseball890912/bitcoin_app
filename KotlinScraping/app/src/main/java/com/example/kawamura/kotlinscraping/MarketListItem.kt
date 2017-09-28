package com.example.kawamura.kotlinscraping

/**
 * Created by kawamura on 2017/09/27.
 */

class MarketListItem {
    var title: String? = null
    var content: String? = null
    var url: String? = null
    constructor(title: String, content: String, url: String) {
        this.title = title
        this.content = content
        this.url = url
    }
    fun setmTitle(title: String) {
        this.title = title
    }
}