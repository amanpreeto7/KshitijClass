package com.o7services.kshitijclass.viewpagers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.o7services.kshitijclass.R
import com.o7services.kshitijclass.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewPagerBinding
    lateinit var viewPagerRecycler: ViewPagerRecycler
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
    }
}
