package com.example.mura.example1.model

import com.example.mura.example1.model.POJO.VKPhoto
import com.example.mura.example1.model.POJO.VKUser
import com.vk.api.sdk.VK
import io.reactivex.Observable

class Requests{

    fun friendRequest(): Observable<List<VKUser>> {
        return Observable.fromCallable {
            VK.executeSync(VKFriendsRequest())
        }
    }


    fun photoRequest(userId:Int): Observable<List<VKPhoto>> {
        return Observable.fromCallable {
            VK.executeSync(VKPhotosRequest(userId))
        }
    }

}
