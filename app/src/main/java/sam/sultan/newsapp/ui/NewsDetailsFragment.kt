package sam.sultan.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sam.sultan.newsapp.database.NewsDataBase
import sam.sultan.newsapp.databinding.FragmentNewsDetailsBinding
import sam.sultan.newsapp.models.Article
import sam.sultan.newsapp.repository.NewsRepository
import sam.sultan.newsapp.viewModel.NewsViewModel
import sam.sultan.newsapp.viewModel.ViewModelFactory

class NewsDetailsFragment : Fragment() {

    lateinit var binding: FragmentNewsDetailsBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()

        val article = arguments?.getSerializable("article") as? Article
        if (article == null){
            Toast.makeText(requireContext(), "Can't find", Toast.LENGTH_SHORT).show()
        }else{
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
        }

        binding.saveButton.setOnClickListener {
            article?.let { it1 -> viewModel.saveArtilce(it1) }
            Toast.makeText(requireContext(), "Successfully saved!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setUpViewModel(){
        val db = NewsDataBase.getDatabase(requireContext()).getNewsDao()
        val repository = NewsRepository(db)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(NewsViewModel::class.java)
    }

}