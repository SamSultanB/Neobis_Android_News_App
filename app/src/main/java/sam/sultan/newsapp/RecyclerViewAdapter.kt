package sam.sultan.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sam.sultan.newsapp.databinding.ItemNewsBinding
import sam.sultan.newsapp.models.Article
import sam.sultan.newsapp.utils.DiffUtils

class RecyclerViewAdapter:  RecyclerView.Adapter<RecyclerViewAdapter.ArticleHolder>(){

    private var newsList: List<Article> = emptyList()

    class ArticleHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var binding = ItemNewsBinding.bind(itemView)
        fun bind(article: Article, position: Int, size: Int) = with(binding){
            Glide.with(binding.image)
                .load(article.urlToImage)
                .into(binding.image)
            title.text = article.title
            description.text = article.description
            author.text = article.author
            publishDate.text = article.publishedAt
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ArticleHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bind(newsList[position], position, newsList.size)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNewsist(newList: List<Article>){
        val diffUtil = DiffUtils(newsList, newList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        this.newsList = newList
        diffUtilResult.dispatchUpdatesTo(this)
    }
}