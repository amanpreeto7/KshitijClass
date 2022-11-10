package com.o7services.kshitijclass.viewpagers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.o7services.kshitijclass.R
import com.o7services.kshitijclass.databinding.ActivityViewPagerBinding
import com.o7services.kshitijclass.recycler.DotsRecycler

class ViewPagerActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewPagerBinding
    lateinit var viewPagerRecycler: ViewPagerRecycler
    lateinit var dotsAdapter: DotsRecycler
    var layoutArray = arrayOf(
        R.layout.activity_app_permissions,
        R.layout.activity_shared_pref,
        R.layout.activity_spinner
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPagerRecycler = ViewPagerRecycler(layoutArray)
        binding.viewPager.adapter = viewPagerRecycler
        dotsAdapter = DotsRecycler(this, 0)

        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dotsAdapter.updatePosition(position)
                Log.e("TAG", " in position $position")
            }
        })
    }
}
