package com.shuheikiyosawa.codingtest.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shuheikiyosawa.codingtest.core.data.TopCategory
import com.shuheikiyosawa.codingtest.view.fragment.TopCategoryFragment

class TopCategoryAdapter(topCategoryList: List<TopCategory>, fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val list = mutableListOf<TopCategory>()
    init {
        list.addAll(topCategoryList)
    }

    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment {
        return TopCategoryFragment.newInstance(list[position])
    }
}
