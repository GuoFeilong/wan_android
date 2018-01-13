package com.android.wan.net.response.entity

/**
 * @author by 有人@我 on 2018/1/13.
 */
class BannerEnity {
    var id: Int = 0
    var url: String? = null
    var imagePath: String? = null
    var title: String? = null
    var desc: String? = null
    var isVisible: Int = 0
    var order: Int = 0
    var type: Int = 0
    override fun toString(): String {
        return "BannerEnity(id=$id, url=$url, imagePath=$imagePath, title=$title, desc=$desc, isVisible=$isVisible, order=$order, type=$type)"
    }


}
