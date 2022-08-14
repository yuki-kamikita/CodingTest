package com.shuheikiyosawa.codingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.shuheikiyosawa.codingtest.core.data.Book
import com.shuheikiyosawa.codingtest.databinding.ActivityMainBinding
import com.shuheikiyosawa.codingtest.view.adapter.TopCategoryAdapter
import com.shuheikiyosawa.codingtest.view.fragment.BookDetailFragment
import com.shuheikiyosawa.codingtest.view.fragment.SearchFragment
import com.shuheikiyosawa.codingtest.viewModel.SearchViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: SearchViewModel by viewModels()

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        supportActionBar!!.hide()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SearchFragment.newInstance())
            .commit()

        viewModel.moveToDetailPage.observe(this, Observer { book ->
            book?.let {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.container, BookDetailFragment.newInstance(
                        name      = book.name,
                        publisher = book.publisher,
                        author    = book.author,
                        imgUrl    = book.img_url
                    ))
                    .commit()
            }
        })

    }
}

// TODO: 詳細ページ作成
// TODO: 保存
// TODO: デザイン調整
//       タイトル、タブ