package com.cyclicsoft.manobotarghor.initialsettings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cyclicsoft.manobotarghor.R
import com.cyclicsoft.manobotarghor.utilites.AppConstants
import com.cyclicsoft.manobotarghor.utilites.PreferenceHelper
import com.cyclicsoft.manobotarghor.utilites.PreferenceHelper.get
import com.cyclicsoft.manobotarghor.utilites.PreferenceHelper.set


class InitialSettingsViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val TAG = InitialSettingsViewModel::class.java.simpleName
    }

    private val preferences = PreferenceHelper.defaultPrefs(getApplication())
    private var _isDataLoaded = MutableLiveData<Boolean>()
    private var _initialScreenList = MutableLiveData<ArrayList<InitialScreenItem>>()

    val isDataLoaded: LiveData<Boolean>
        get() = _isDataLoaded

    val initialScreenList: LiveData<ArrayList<InitialScreenItem>>
        get() = _initialScreenList

    private fun loadInitialFile() {

    }

    fun loadData() {
        loadInitialFile()
        reqInitialScreensList()
        _isDataLoaded.value = true
    }

    fun isLangDialogShowed(): Boolean {
        return preferences[AppConstants.KEY_IS_LANG_SETTING_DIALOG_SHOWN, false]!!
    }

    fun setLangDialogShowed() {
        preferences[AppConstants.KEY_IS_LANG_SETTING_DIALOG_SHOWN] = true
    }

    private fun reqInitialScreensList() {
        val initialScreensList = ArrayList<InitialScreenItem>()
        val bgIDArray: IntArray = intArrayOf(
            R.drawable.bg_intro_donate,
            R.drawable.bg_intro_search_donar,
            R.drawable.bg_intro_explore
        )
        val titleIDArray: IntArray =
            intArrayOf(R.string.txt_donate, R.string.txt_search_donar, R.string.txt_explore)
        val descIDArray: IntArray = intArrayOf(
            R.string.txt_donate_description,
            R.string.txt_search_donar_description,
            R.string.txt_explore_description
        )
        for (i in bgIDArray.indices) {
            val initialScreenItem = InitialScreenItem(bgIDArray[i], titleIDArray[i], descIDArray[i])
            initialScreensList.add(initialScreenItem)
        }
        _initialScreenList.value = initialScreensList
    }

    fun setInitialSettingFinished() {
        preferences[AppConstants.KEY_IS_INITIALIZED] = true
    }
}