package com.tapaafandi.consumergithubapp.presentation.favorit_list

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tapaafandi.consumergithubapp.DatabaseContract
import com.tapaafandi.consumergithubapp.MappingHelper
import com.tapaafandi.consumergithubapp.domain.model.User

class MainViewModel(application: Application): AndroidViewModel(application) {

    private var userList = MutableLiveData<ArrayList<User>>()

    fun setUserFavorite(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val listMapped = MappingHelper.mapCursorToArrayList(cursor)
        userList.postValue(listMapped)
    }

    fun getUserFavorite(): LiveData<ArrayList<User>> {
        return userList
    }
}