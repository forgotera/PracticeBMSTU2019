package com.example.mura.example1.model.POJO

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class VKPhoto(
    //id photo
    val id: Int,
    //id владельца фото
    val ownerId:Int,
    val text:String,
    val date:Int,
    val url:String,
    val likes_count:Int
    ):Parcelable{

    constructor(parcel:Parcel): this (
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(ownerId)
        parcel.writeString(text)
        parcel.writeInt(date)
        parcel.writeString(url)
        parcel.writeInt(likes_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VKPhoto> {
        override fun createFromParcel(parcel: Parcel): VKPhoto {
            return VKPhoto(parcel)
        }

        override fun newArray(size: Int): Array<VKPhoto?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject)
                = VKPhoto(
            id = json.optInt("id", 0),
            ownerId = json.optInt("owner_id", 0),
            text = json.optString("text", ""),
            date = json.optInt("date", 0),
            url = (json.getJSONArray("sizes")).getJSONObject(4).optString("url", ""),
            likes_count = (json.getJSONObject("likes")).optInt("count", 0)
        )
    }


}