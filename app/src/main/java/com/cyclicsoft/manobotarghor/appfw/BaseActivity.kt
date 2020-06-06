package com.cyclicsoft.manobotarghor.appfw

import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity

abstract class BaseActivity: LocalizationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Do not change calling order
        createView(savedInstanceState)
        setUpToolBar()
        initViewModel()
        bindViews()
        observeViewModelData()
        initListeners()
        bindData()
        //Do not change calling order
    }

    abstract fun createView(
        savedInstanceState: Bundle?
    )
    abstract fun setUpToolBar()
    abstract fun initViewModel()
    abstract fun bindViews()
    abstract fun observeViewModelData()
    abstract fun initListeners()
    abstract fun bindData()
}