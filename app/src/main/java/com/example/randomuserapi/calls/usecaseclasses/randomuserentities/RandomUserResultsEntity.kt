package com.example.randomuserapi.calls.usecaseclasses.randomuserentities

import com.google.gson.annotations.SerializedName

class RandomUserResultsEntity(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("name")
    val name: RandomUserNameEntity?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("picture")
    val picture: RandomUserPictureEntity?
)