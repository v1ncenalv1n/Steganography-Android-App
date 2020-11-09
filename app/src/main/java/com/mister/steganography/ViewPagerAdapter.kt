package com.mister.steganography

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(supportfragmentmanager: FragmentManager) : FragmentPagerAdapter(supportfragmentmanager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val mFragmentlist = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentlist[position]
    }

    override fun getCount(): Int {
        return mFragmentlist.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
    fun addFragment(fragment : Fragment, title: String){
        mFragmentlist.add(fragment)
        mFragmentTitleList.add(title)
    }
}