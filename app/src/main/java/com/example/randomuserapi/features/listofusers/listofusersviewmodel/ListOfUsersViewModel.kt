package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.content.Context
import android.service.autofill.UserData
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapi.calls.randomuserusecase.RandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserEntity
import com.example.randomuserapi.calls.usecaseclasses.randomuserentities.RandomUserResultsEntity
import com.example.randomuserapi.features.listofusers.listofusersactivity.ListOfUsersActivity
import com.example.randomuserapi.utils.TransformEntity
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Random

class ListOfUsersViewModel: ViewModel(), ListOfUsersViewModelInterface {

    lateinit var randomUserUseCase: RandomUserUseCase
    lateinit var listOfUsers: ArrayList<RandomUser>

    lateinit var usuarioDePrueba: RandomUser

    override fun initializeViewModel(){
        this.listOfUsers = ArrayList()
        this.randomUserUseCase = RandomUserUseCase()
    }

    override fun randomUserCall(numberOfUsers: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            val retrofitInstance = randomUserUseCase.retrofitBuilderRandomUserInstance()

            for(number in 0..numberOfUsers) {

                val response = retrofitInstance?.getRandomUserDataCall()

                var randomUser: RandomUser? = null

                if (response != null) {
                    randomUser = TransformEntity.fromEntityToUser(response)
                }

                if(randomUser != null){
                    listOfUsers.add(randomUser)
                }

                Log.d("","User recibido -> "+randomUser?.firstName+" "+randomUser?.lastName)

            }

        }


    }

    fun getRandomUserList():ArrayList<RandomUser>{
        return this.listOfUsers
    }



}