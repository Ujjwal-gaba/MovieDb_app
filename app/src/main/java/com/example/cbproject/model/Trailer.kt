package com.example.cbproject.model

import com.google.gson.annotations.SerializedName


//class Trailer( var key: String?, var name: String?){
//    internal fun getKey(): String? {
//        return key
//    }
//
//    internal fun setKey(key: String?) {
//        this.key = key
//    }
//
//    internal fun getName(): String? {
//        return name
//    }
//
//    internal fun setName(name: String?) {
//        this.name = name
//    }
//}

class Trailer(
    @field:SerializedName("key")
    var key: String?, @field:SerializedName("name")
    var name: String?
) {
    internal fun getKey(): String? {
        return key
    }

    internal fun setKey(key: String?) {
        this.key = key
    }

    internal fun getName(): String? {
        return name
    }

    internal fun setName(name: String?) {
        this.name = name
    }
}

