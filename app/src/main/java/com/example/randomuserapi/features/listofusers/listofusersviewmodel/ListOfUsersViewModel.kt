package com.example.randomuserapi.features.listofusers.listofusersviewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapi.calls.domain.GetRandomUserUseCase
import com.example.randomuserapi.calls.usecaseclasses.randomuserclass.RandomUser
import com.example.randomuserapi.utils.TransformEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfUsersViewModel @Inject constructor(
    private val randomUserUseCase: GetRandomUserUseCase
): ViewModel(), ListOfUsersViewModelInterface {

    private var TAG = this.javaClass.name
    private var userRecievedFromApiCall: RandomUser? = null
    private var userListPrepared: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private lateinit var listOfUsers: ArrayList<RandomUser>

    override fun initializeViewModel(){
        this.userListPrepared.value = false
        this.listOfUsers = ArrayList()
    }

    override fun randomUserApiCall(numberOfUsers: Int) {

        viewModelScope.launch {

            val result = randomUserUseCase()

            for(position in 0 until numberOfUsers) {

                if (result != null) {

                        userRecievedFromApiCall =
                            result.let { TransformEntity.fromEntityToUser(it) }

                        userRecievedFromApiCall?.let { listOfUsers.add(it) }

                        if (position + 1 == numberOfUsers) {
                            userListPrepared.postValue(true)
                        }

                    }
                }
            }
    }

    override fun getRandomUserList(): ArrayList<RandomUser> {
        return this.listOfUsers
    }

    override fun getUserListPreparedValue(): MutableLiveData <Boolean> {
        return this.userListPrepared
    }

}