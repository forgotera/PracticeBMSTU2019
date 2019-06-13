package com.example.mura.example1.model

import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException

class Requests {

    fun getFriend(){
        val friends: MutableList<VKUser> = arrayListOf()
        VK.execute(VKFriendsRequest(), object : VKApiCallback<List<VKUser>> {
            override fun success(result: List<VKUser>) {
                friends.addAll(result)
            }

            override fun fail(error: VKApiExecutionException) {
            }
        })
    }
}