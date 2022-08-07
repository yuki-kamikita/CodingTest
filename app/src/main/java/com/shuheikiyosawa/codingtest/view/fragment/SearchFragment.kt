package com.shuheikiyosawa.codingtest.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.shuheikiyosawa.codingtest.databinding.FragmentSearchBinding
import com.shuheikiyosawa.codingtest.view.adapter.TopCategoryAdapter
import com.shuheikiyosawa.codingtest.viewModel.SearchViewModel

/**
 * 見つける
 *
 * タブレイアウトについて書いてある
 */
class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // タブ周り
        viewModel.response.observe(viewLifecycleOwner, Observer { value ->
            value?.let {
                val viewPager = binding.viewPager
                val tabLayout = binding.tabLayout
                viewPager.adapter = TopCategoryAdapter(value.topCategoryList, parentFragmentManager, lifecycle)
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = value.topCategoryList[position].name
                }.attach()
            }
        })

        return binding.root

    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}