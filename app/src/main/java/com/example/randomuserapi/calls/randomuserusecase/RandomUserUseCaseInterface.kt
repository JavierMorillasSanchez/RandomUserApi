package com.example.randomuserapi.calls.randomuserusecase

import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.utils.ApiUrl
import retrofit2.Response
import retrofit2.http.GET

interface RandomUserUseCaseInterface {
    @GET(ApiUrl.randomUserApiUrl)
    suspend fun getRandomUserDataCall(): Response<RandomUserEntity>
}