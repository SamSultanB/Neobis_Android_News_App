package sam.sultan.newsapp.utils

import androidx.recyclerview.widget.DiffUtil
import sam.sultan.newsapp.models.Article

class DiffUtils(
    private val oldList: List<Article>,
    private val newList: List<Article>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].title != newList[newItemPosition].title -> false
            oldList[oldItemPosition].description != newList[newItemPosition].description -> false
            oldList[oldItemPosition].urlToImage != newList[newItemPosition].urlToImage -> false
            oldList[oldItemPosition].author != newList[newItemPosition].author -> false
            oldList[oldItemPosition].publishedAt != newList[newItemPosition].publishedAt -> false
            else -> true
        }
    }


}