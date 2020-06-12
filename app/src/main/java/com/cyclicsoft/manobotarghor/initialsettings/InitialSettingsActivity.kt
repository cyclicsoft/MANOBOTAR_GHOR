package com.cyclicsoft.manobotarghor.initialsettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.cyclicsoft.manobotarghor.home.MainActivity
import com.cyclicsoft.manobotarghor.R
import com.cyclicsoft.manobotarghor.appfw.BaseActivity
import com.cyclicsoft.manobotarghor.appfw.CSLog
import com.cyclicsoft.manobotarghor.utilites.AppConstants
import com.cyclicsoft.manobotarghor.utilites.Util
import kotlinx.android.synthetic.main.initial_settings_activity.*
import kotlinx.android.synthetic.main.language_selection_dialog.*
import kotlinx.android.synthetic.main.language_selection_dialog.view.*

class InitialSettingsActivity : BaseActivity() {
    private lateinit var mViewModel: InitialSettingsViewModel
    private lateinit var mInitialPagerAdapter: InitialPagerAdapter

    companion object {
        val TAG = InitialSettingsActivity::class.java.simpleName
    }

    override fun createView(savedInstanceState: Bundle?) {
        setContentView(R.layout.initial_settings_activity)
    }

    override fun setUpToolBar() {}

    override fun bindViews() {
        if (!mViewModel.isLangDialogShowed()) {
            showLanguageSelectionDialog()
        }
    }

    override fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(InitialSettingsViewModel::class.java)
    }

    override fun observeViewModelData() {
        mViewModel.initialScreenList.observe(this, Observer {
            mInitialPagerAdapter = InitialPagerAdapter(this, it)
            vp_initial_screens.adapter = mInitialPagerAdapter
            dots_indicator.setViewPager2(vp_initial_screens)
        })
    }

    override fun initListeners() {
        vp_initial_screens.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                CSLog.d(TAG, "Item Position: $position")
                if (position == (AppConstants.SIZE_INITIAL_SETTING_PAGES - 1)) {
                    tv_finish_initial_screen.visibility = View.VISIBLE
                } else {
                    tv_finish_initial_screen.visibility = View.GONE
                }
            }


        })
        tv_finish_initial_screen.setOnClickListener {
            mViewModel.setInitialSettingFinished()
            Util.gotoActivity(this@InitialSettingsActivity,
                MainActivity(), false)
        }


    }

    override fun bindData() {
        mViewModel.loadData()
    }

    private fun showLanguageSelectionDialog() {
        //Inflate the dialog with custom view
        val mDialogView =
            LayoutInflater.from(this).inflate(R.layout.language_selection_dialog, null)
        mDialogView?.tv_bn?.isSelected = true
        mDialogView?.tv_en?.isSelected = false
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle(getString(R.string.txt_slect_lang))
        //show dialog
        val mAlertDialog = mBuilder.show()
        mAlertDialog.setCancelable(false)
        mDialogView?.tv_bn?.setOnClickListener {
            CSLog.d(TAG, "mDialogView.tv_bn.setOnClickListener")
            mDialogView.tv_bn.isSelected = true
            mDialogView.tv_bn.setTextColor(getColor(R.color.color_dialog_item_txt_selected))
            mDialogView.tv_en.isSelected = false
            mDialogView.tv_en.setTextColor(getColor(R.color.color_dialog_item_txt_unselected))
        }
        mDialogView?.tv_en?.setOnClickListener {
            CSLog.d(TAG, "mDialogView.tv_en.setOnClickListener")
            mDialogView.tv_bn.isSelected = false
            mDialogView.tv_bn.setTextColor(getColor(R.color.color_dialog_item_txt_unselected))
            mDialogView.tv_en.isSelected = true
            mDialogView.tv_en.setTextColor(getColor(R.color.color_dialog_item_txt_selected))
        }
        mDialogView?.btn_ok?.setOnClickListener {
            CSLog.d(TAG, "mDialogView?.btn_ok?.setOnClickListener")
            //dismiss dialog
            if (mAlertDialog.tv_bn.isSelected) {
                setLanguage(AppConstants.LANG_CODE_BN)
            } else {
                setLanguage(AppConstants.LANG_CODE_EN)
            }
            mViewModel.setLangDialogShowed()
            mAlertDialog.dismiss()
        }
    }
}
