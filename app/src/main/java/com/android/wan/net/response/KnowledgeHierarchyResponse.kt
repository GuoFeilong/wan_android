package com.android.wan.net.response

import java.io.Serializable

/**
 * @author by 有人@我 on 18/1/16.
 */
data class KnowledgeHierarchyResponse(var errorCode: Int,
                                      var errorMsg: String?,
                                      var data: List<Data>) {
    data class Data(var id: Int,
                    var name: String,
                    var courseId: Int,
                    var parentChapterId: Int,
                    var order: Int,
                    var visible: Int,
                    var children: List<Children>?) : Serializable {
        data class Children(var id: Int,
                            var name: String,
                            var courseId: Int,
                            var parentChapterId: Int,
                            var order: Int,
                            var visible: Int,
                            var children: List<Children>?) : Serializable
    }
}