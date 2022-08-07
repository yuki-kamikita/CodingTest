package com.shuheikiyosawa.codingtest.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuheikiyosawa.codingtest.R
import com.shuheikiyosawa.codingtest.core.data.SubCategory

class SubCategoryAdapter (private val context: Context, private val subCategoryList: List<SubCategory>):
    RecyclerView.Adapter<SubCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_sub_category, parent, false)
        return SubCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.subCategoryTitle.text = subCategoryList[position].name

        holder.bookList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = BookListAdapter(context, subCategoryList[position].bookList)
        }

    }

    override fun getItemCount() = subCategoryList.size
}

class SubCategoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val subCategoryTitle: TextView by lazy {
        view.findViewById(R.id.sub_category_title)
    }
    val bookList: RecyclerView by lazy {
        view.findViewById(R.id.book_list_recycler_view)
    }

}