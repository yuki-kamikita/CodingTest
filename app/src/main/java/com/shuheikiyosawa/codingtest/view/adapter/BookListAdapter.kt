package com.shuheikiyosawa.codingtest.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shuheikiyosawa.codingtest.R
import com.shuheikiyosawa.codingtest.core.data.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BookListAdapter (
    private val context: Context,
    private val bookList: List<Book>,
    private val tapBookThumbnail: (book: Book) -> Unit
): RecyclerView.Adapter<BookListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_book_list, parent, false)
        return BookListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val bitmap = Glide.with(context)
                .asBitmap()
                .load(bookList[position].img_url)
                .submit()
                .get()
            GlobalScope.launch(Dispatchers.Main) {
                holder.bookThumbnail.setImageBitmap(bitmap)
                holder.bookThumbnail.setOnClickListener {
//                    MainActivity().moveDetailPage(bookList[position])
                    tapBookThumbnail(bookList[position])
                }
            }
        }
    }

    override fun getItemCount() = bookList.size
}

class BookListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val bookThumbnail: ImageView by lazy {
        view.findViewById(R.id.book_thumbnail)
    }

}