package com.example.mura.example1.model

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKPhotosRequest(userId:Int = 0):VKRequest<List<VKPhoto>>("photos.getAll") {
    init {
        if (userId != 0){
            addParam("owner_id",userId)
            addParam("extended",1)
        }
    }

    override fun parse(r: JSONObject): List<VKPhoto> {
        val photoInfo = r.getJSONObject("response").getJSONArray("items")
        val result = ArrayList<VKPhoto>()
        for (i in 0 until photoInfo.length()) {
            result.add(VKPhoto.parse(photoInfo.getJSONObject(i)))
        }
        return result
    }
}