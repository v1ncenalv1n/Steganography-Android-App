package com.mister.steganography

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayush.imagesteganographylibrary.Text.AsyncTaskCallback.TextEncodingCallback
import com.ayush.imagesteganographylibrary.Text.ImageSteganography
import com.mister.steganography.fragment.DecodeFragment
import com.mister.steganography.fragment.EncodeFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            setSupportActionBar(toolbar)
            supportActionBar!!.title= "Steganography"
            setuptab()
        }

    private fun setuptab() {
        val adapter =  ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(EncodeFragment(),"Encode")
        adapter.addFragment(DecodeFragment(),"Decode")

        viewpager.adapter=adapter
        tabs.setupWithViewPager(viewpager)
    }

}
