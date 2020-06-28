package com.cyclicsoft.manobotarghor.home.ui.phoneverification

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyclicsoft.manobotarghor.R
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.phone_verification_fragment.*

class PhoneVerificationFragment : Fragment() {

    companion object {
        fun newInstance() = PhoneVerificationFragment()
    }

    private lateinit var viewModel: PhoneVerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.phone_verification_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstPinView.setAnimationEnable(true)
        tv_get_otp_btn.setOnClickListener {
            YoYo.with(Techniques.SlideOutLeft)
                .duration(600)
                .onEnd {
                    cl_add_phone_container.visibility = View.INVISIBLE
                    cl_otp_container.visibility = View.VISIBLE
                }
                .playOn(cl_add_phone_container)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PhoneVerificationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}