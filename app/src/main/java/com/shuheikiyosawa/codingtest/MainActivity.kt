package com.shuheikiyosawa.codingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shuheikiyosawa.codingtest.databinding.ActivityMainBinding
import com.shuheikiyosawa.codingtest.view.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        supportActionBar!!.hide()

        supportFragmentManager
            .beginTransaction()
//            addToBackStack(null)
            .replace(R.id.container, SearchFragment.newInstance())
            .commit()

    }
}