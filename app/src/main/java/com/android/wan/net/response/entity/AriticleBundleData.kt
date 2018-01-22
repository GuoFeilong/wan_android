package com.android.wan.net.response.entity

import java.io.Serializable

/**
 * @author by 有人@我 on 18/1/22.
 */
class AriticleBundleData : Serializable {
    var typeTitle: String? = null
    var typeList: List<AriticleTypeData>? = null

    class AriticleTypeData : Serializable {
        var typeName: String? = null
        var typeCid: Int = 0
    }
}