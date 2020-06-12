package com.cyclicsoft.manobotarghor.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.cyclicsoft.manobotarghor.R
import com.cyclicsoft.manobotarghor.appfw.BaseActivity
import com.cyclicsoft.manobotarghor.home.ui.HomeFragment
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*

class MainActivity : BaseActivity() {

    override fun createView(savedInstanceState: Bundle?) {
        setStatusBarColor()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, HomeFragment.newInstance(), HomeFragment::class.java.simpleName)
                .commit()
        }
    }

    override fun setUpToolBar() {
        setSupportActionBar(toolbar)
        img_toolbar_left?.setImageResource(R.drawable.ic_menu_white_24dp)
        img_toolbar_left?.visibility = View.VISIBLE

        toolbar_title?.setTextColor(getColor(R.color.color_title_home))

        img_toolbar_right?.setImageResource(R.drawable.ic_search_white_24dp)
        img_toolbar_right?.visibility = View.VISIBLE
    }

    override fun initViewModel() {
    }

    override fun bindViews() {
        setupBlurView()
    }

    override fun observeViewModelData() {
    }

    override fun initListeners() {
    }

    override fun bindData() {
    }

    private fun setStatusBarColor(){
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        val drawable: Drawable? = this.getDrawable(R.drawable.bg_gradiant)
        window.statusBarColor = this.getColor(android.R.color.transparent)
        window.navigationBarColor  = this.getColor(android.R.color.transparent)
        window.setBackgroundDrawable(drawable)
    }

    private fun setupBlurView() {
        val radius = 12f
        val minBlurRadius = 10f
        val step = 4f
        //set background, if your root layout doesn't have one
        val windowBackground = window.decorView.background
        blur_view.setupWith(root)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setHasFixedTransformationMatrix(true)
    }
}
