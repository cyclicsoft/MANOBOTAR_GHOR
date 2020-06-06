package com.cyclicsoft.manobotarghor.splash

import android.animation.Animator
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cyclicsoft.manobotarghor.MainActivity
import com.cyclicsoft.manobotarghor.R
import com.cyclicsoft.manobotarghor.appfw.BaseActivity
import com.cyclicsoft.manobotarghor.initialsettings.InitialSettingsActivity
import com.cyclicsoft.manobotarghor.utilites.Util
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    private lateinit var splashViewModel: SplashViewModel
    private var mIsInitialized = false
    override fun createView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_splash)
    }

    override fun setUpToolBar() {
    }

    override fun bindViews() {
    }

    override fun initViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun observeViewModelData() {
        splashViewModel.isDataLoaded.observe(this, Observer {
            anim_loading.repeatCount = 1
        })

        splashViewModel.isInitialized.observe(this, Observer {
            mIsInitialized =it
        })
    }

    override fun initListeners() {
        anim_loading?.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                if(mIsInitialized){
                    Util.gotoActivity(this@SplashActivity, MainActivity(), false)
                }else{
                    Util.gotoActivity(this@SplashActivity, InitialSettingsActivity(), false)
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
                if(mIsInitialized){
                    Util.gotoActivity(this@SplashActivity, MainActivity(), false)
                }else{
                    Util.gotoActivity(this@SplashActivity, InitialSettingsActivity(), false)
                }
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }

    override fun bindData() {
        splashViewModel.loadData()
    }
}
