package com.cyclicsoft.manobotarghor.initialsettings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyclicsoft.manobotarghor.R
import com.google.android.material.textview.MaterialTextView

class InitialPagerAdapter(
    private val mContext: Context,
    private val mInitialItemList: List<InitialScreenItem>
) : RecyclerView.Adapter<InitialPagerAdapter.InitialItemHolder>() {

    class InitialItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvTitle = itemView.findViewById(R.id.tv_title_init_screen) as MaterialTextView
        val mTvDesc = itemView.findViewById(R.id.tv_desc_init_screen) as MaterialTextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InitialItemHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val rootView = layoutInflater.inflate(R.layout.layout_initial_viewpager_item, parent, false)
        return InitialItemHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mInitialItemList.size
    }

    override fun onBindViewHolder(holder: InitialItemHolder, position: Int) {
        holder.itemView.background = mContext.getDrawable(mInitialItemList[position].mBgID)
        holder.mTvTitle.text = mContext.getString(mInitialItemList[position].mTitleID)
        holder.mTvDesc.text = mContext.getString(mInitialItemList[position].mDescID)
    }
}