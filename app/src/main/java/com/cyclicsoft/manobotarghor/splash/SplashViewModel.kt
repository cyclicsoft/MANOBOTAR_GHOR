package com.cyclicsoft.manobotarghor.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cyclicsoft.manobotarghor.utilites.AppConstants
import com.cyclicsoft.manobotarghor.utilites.PreferenceHelper
import com.cyclicsoft.manobotarghor.utilites.PreferenceHelper.get


class SplashViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val TAG = SplashViewModel::class.java.simpleName
    }

    private val preferences = PreferenceHelper.defaultPrefs(getApplication())
    private var _isDataLoaded = MutableLiveData<Boolean>()
    private var _isInitialized = MutableLiveData<Boolean>()

    val isDataLoaded: LiveData<Boolean>
        get() = _isDataLoaded

    val isInitialized: LiveData<Boolean>
        get() = _isInitialized

    private fun loadInitializationInfo() {
        _isInitialized.value = preferences[AppConstants.KEY_IS_INITIALIZED, false]
    }

    fun loadData() {
        loadInitializationInfo()
        _isDataLoaded.value = true
    }

}