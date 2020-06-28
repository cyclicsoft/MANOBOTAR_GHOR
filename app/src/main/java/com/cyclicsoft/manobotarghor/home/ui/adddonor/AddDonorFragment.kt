package com.cyclicsoft.manobotarghor.home.ui.adddonor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.cyclicsoft.manobotarghor.R
import kotlinx.android.synthetic.main.add_donor_fragment.*

class AddDonorFragment : Fragment() {

    companion object {
        fun newInstance() = AddDonorFragment()
    }

    private lateinit var mViewModel: AddDonorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_donor_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_group_item_b_minus?.isSelected = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(AddDonorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
