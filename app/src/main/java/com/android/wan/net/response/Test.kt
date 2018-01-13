package com.android.wan.net.response

/**
 * Created by Jsion on 2018/1/13.
 */

class Test {

    /**
     * errorCode : 0
     * errorMsg : null
     * data : [{"id":6,"url":"http://www.wanandroid.com/navi","imagePath":"http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png","title":"我们新增了一个常用导航Tab~","desc":"","isVisible":1,"order":1,"type":0},{"id":7,"url":"http://www.wanandroid.com/blog/show/10","imagePath":"http://www.wanandroid.com/blogimgs/ffb61454-e0d2-46e7-bc9b-4f359061ae20.png","title":"送你一个暖心的Mock API工具","desc":"","isVisible":1,"order":2,"type":0},{"id":3,"url":"http://www.wanandroid.com/article/list/0?cid=254","imagePath":"http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png","title":"兄弟，要不要挑个项目学习下?","desc":"","isVisible":1,"order":3,"type":0},{"id":4,"url":"http://www.wanandroid.com/article/list/0?cid=73","imagePath":"http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png","title":"看看别人的面经，搞定面试~","desc":"","isVisible":1,"order":3,"type":0},{"id":2,"url":"http://www.wanandroid.com/tools/bejson","imagePath":"http://www.wanandroid.com/blogimgs/90cf8c40-9489-4f9d-8936-02c9ebae31f0.png","title":"JSON工具","desc":"","isVisible":1,"order":2,"type":1},{"id":5,"url":"http://www.wanandroid.com/blog/show/6","imagePath":"http://www.wanandroid.com/blogimgs/acc23063-1884-4925-bdf8-0b0364a7243e.png","title":"微信文章合集","desc":"","isVisible":1,"order":3,"type":1}]
     */

    var errorCode: Int = 0
    var errorMsg: Any? = null
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * id : 6
         * url : http://www.wanandroid.com/navi
         * imagePath : http://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png
         * title : 我们新增了一个常用导航Tab~
         * desc :
         * isVisible : 1
         * order : 1
         * type : 0
         */

        var id: Int = 0
        var url: String? = null
        var imagePath: String? = null
        var title: String? = null
        var desc: String? = null
        var isVisible: Int = 0
        var order: Int = 0
        var type: Int = 0
    }
}
