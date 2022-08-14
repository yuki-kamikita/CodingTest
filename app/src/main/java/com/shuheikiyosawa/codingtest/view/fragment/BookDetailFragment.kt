package com.shuheikiyosawa.codingtest.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.shuheikiyosawa.codingtest.R
import com.shuheikiyosawa.codingtest.databinding.FragmentBookDetailBinding
import com.shuheikiyosawa.codingtest.viewModel.BookDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val ARG_NAME = "name"
private const val ARG_PUBLISHER = "publisher"
private const val ARG_AUTHOR = "author"
private const val ARG_IMAGE_URL = "imageUrl"

class BookDetailFragment : Fragment() {
    private var name: String? = null
    private var publisher: String? = null
    private var author: String? = null
    private var imageUrl: String? = null

    private val viewModel: BookDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_NAME)
            publisher = it.getString(ARG_PUBLISHER)
            author = it.getString(ARG_AUTHOR)
            imageUrl = it.getString(ARG_IMAGE_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.appbar.title = "書籍詳細"
        binding.appbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.appbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.name.text = name
        binding.publisher.text = "出版社：$publisher"
        binding.author.text = "著者：$author"
        GlobalScope.launch(Dispatchers.IO) {
            val bitmap = Glide.with(requireActivity())
                .asBitmap()
                .load(imageUrl)
                .submit()
                .get()
            GlobalScope.launch(Dispatchers.Main) {
                binding.bookImage.setImageBitmap(bitmap)
            }
        }

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding
//    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, publisher: String, author: String, imgUrl: String) =
            BookDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_PUBLISHER, publisher)
                    putString(ARG_AUTHOR, author)
                    putString(ARG_IMAGE_URL, imgUrl)
                }
            }
    }
}