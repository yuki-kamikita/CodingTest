package com.shuheikiyosawa.codingtest.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.shuheikiyosawa.codingtest.core.data.TopCategory
import com.shuheikiyosawa.codingtest.databinding.FragmentTopCategoryBinding
import com.shuheikiyosawa.codingtest.view.adapter.SubCategoryAdapter

private const val TOP_CATEGORY = "top_category"

class TopCategoryFragment : Fragment() {
    private var category: TopCategory? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val gson = Gson()
            category = gson.fromJson(it.getString(TOP_CATEGORY), TopCategory::class.java) as TopCategory
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTopCategoryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = SubCategoryAdapter(context, category!!.subCategoryList)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(topCategory: TopCategory) =
            TopCategoryFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    putString(TOP_CATEGORY, gson.toJson(topCategory))
                }
            }
    }
}