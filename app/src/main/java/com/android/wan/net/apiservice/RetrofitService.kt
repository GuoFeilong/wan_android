import com.android.wan.net.response.*
import retrofit2.http.*
import rx.Observable

/**
 * Retrofit请求玩AndroidAPI
 */
interface RetrofitService {

    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     * @param page page
     */

    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") page: Int): Observable<HomeListResponse>

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("/tree/json")
    fun getKnowledgeHierarchyList(): Observable<KnowledgeHierarchyResponse>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page page
     * @param cid cid
     */
    @GET("/article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<ArticleListResponse>

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     */
    @GET("/friend/json")
    fun getFriendList(): Observable<FriendListResponse>

    /**
     * 大家都在搜
     * http://www.wanandroid.com/hotkey/json
     */
    @GET("/hotkey/json")
    fun getHotKeyList(): Observable<HotKeyResponse>

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page page
     * @param k POST search key
     */
    @POST("/article/query/{page}/json")
    @FormUrlEncoded
    fun getSearchList(@Path("page") page: Int, @Field("k") k: String): Observable<HomeListResponse>

    /**
     * 登录
     * @param username username
     * @param password password
     * @return Deferred<LoginResponse>
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun loginWanAndroid(
            @Field("username") username: String,
            @Field("password") password: String)
            : Observable<LoginResponse>

    /**
     * 注册
     * @param username username
     * @param password password
     * @param repassword repassword
     * @return Deferred<LoginResponse>
     */
    @POST("/user/register")
    @FormUrlEncoded
    fun registerWanAndroid(
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("repassword") repassowrd: String)
            : Observable<LoginResponse>

    /**
     * 获取自己收藏的文章列表
     * @param page page
     * @return Deferred<HomeListResponse>
     */
    @GET("/lg/collect/list/{page}/json")
    fun getLikeList(
            @Path("page") page: Int
    ): Observable<HomeListResponse>

    /**
     * 收藏文章
     * @param id id
     * @return Deferred<HomeListResponse>
     */
    @POST("/lg/collect/{id}/json")
    fun addCollectArticle(
            @Path("id") id: Int
    ): Observable<HomeListResponse>

    /**
     * 收藏站外文章
     * @param title title
     * @param author author
     * @param link link
     * @return Deferred<HomeListResponse>
     */
    @POST("/lg/collect/add/json")
    @FormUrlEncoded
    fun addCollectOutsideArticle(
            @Field("title") title: String,
            @Field("author") author: String,
            @Field("link") link: String
    ): Observable<HomeListResponse>

    /**
     * 删除收藏文章
     * @param id id
     * @param originId -1
     * @return Deferred<HomeListResponse>
     */
    @POST("/lg/uncollect/{id}/json")
    @FormUrlEncoded
    fun removeCollectArticle(
            @Path("id") id: Int,
            @Field("originId") originId: Int = -1
    ): Observable<HomeListResponse>

    /**
     * 首页Banner
     * @return BannerResponse
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BannerResponse>

}