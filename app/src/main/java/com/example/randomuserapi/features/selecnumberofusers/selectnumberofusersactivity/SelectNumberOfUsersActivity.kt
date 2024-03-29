package com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersactivity


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.randomuserapi.R
import com.example.randomuserapi.databinding.ActivitySelectNumberOfUsersBinding
import com.example.randomuserapi.features.selecnumberofusers.selectnumberofusersviewmodel.SelectNumberOfUsersViewModel
import com.example.randomuserapi.utils.InfoDialog
import com.example.randomuserapi.utils.NetworkState

class SelectNumberOfUsersActivity : AppCompatActivity(), SelectNumberOfUsersActivityInterface {

    var logTag = this::class.java.toString()

    private lateinit var viewmodel: SelectNumberOfUsersViewModel
    private lateinit var binding: ActivitySelectNumberOfUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivitySelectNumberOfUsersBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.viewmodel = SelectNumberOfUsersViewModel()
        this.viewmodel.initializeViewModel()
        initializeUI()
    }

    override fun initializeUI() {
        this.binding.btnShowUsers.setOnClickListener {
            checkNetworkStateToShowUsers()
        }
    }

    override fun getNumberOfUsers(): Int {

        var numberOfUsersInput = this.binding.etxtIntroduceNumberOfUsers.text.toString()

        try {
            return Integer.parseInt(numberOfUsersInput)
        } catch (e:Exception){
            Toast.makeText(applicationContext, R.string.toast_too_many_users, Toast.LENGTH_SHORT).show()
        }

        return 0
    }

    override fun checkNumberOfUsers(numberOfUsers: Int) = when {
            numberOfUsers == 0 -> Toast.makeText(applicationContext, R.string.toast_introduce_number_of_users_to_show, Toast.LENGTH_SHORT).show()
            numberOfUsers >= 51 -> Toast.makeText(applicationContext, R.string.toast_too_many_users, Toast.LENGTH_SHORT).show()
            else -> this.viewmodel.navigateToListOfUsers(applicationContext, getNumberOfUsers())
    }

    override fun checkNetworkStateToShowUsers(){
        if(NetworkState.isOnline(applicationContext, logTag)){
            checkNumberOfUsers(getNumberOfUsers())
        } else {
            InfoDialog.createDialog().customDialog(this)
        }
    }

}



