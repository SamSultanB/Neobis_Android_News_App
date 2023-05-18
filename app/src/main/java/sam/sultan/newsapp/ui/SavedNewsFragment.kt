package sam.sultan.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import sam.sultan.newsapp.R
import sam.sultan.newsapp.RecyclerViewAdapter
import sam.sultan.newsapp.database.NewsDataBase
import sam.sultan.newsapp.databinding.FragmentSavedNewsBinding
import sam.sultan.newsapp.models.Article
import sam.sultan.newsapp.repository.NewsRepository
import sam.sultan.newsapp.viewModel.NewsViewModel
import sam.sultan.newsapp.viewModel.ViewModelFactory

class SavedNewsFragment : Fragment() {

    lateinit var binding: FragmentSavedNewsBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpRV()
        viewModel.savedArticles.observe(viewLifecycleOwner, Observer{ articles ->
            adapter.setNewsist(articles)
        })

        adapter.clickToDetails = { clickToDetails(it) }

    }

    private fun setUpViewModel(){
        val db = NewsDataBase.getDatabase(requireContext()).getNewsDao()
        val repository = NewsRepository(db)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(NewsViewModel::class.java)
    }

    private fun setUpRV(){
        adapter = RecyclerViewAdapter()
        binding.savedRV.layoutManager = LinearLayoutManager(context)
        binding.savedRV.adapter = adapter
    }

    private fun clickToDetails(article: Article){
        val bundle = Bundle()
        bundle.putSerializable("article", article)
        findNavController().navigate(R.id.action_savedNewsFragment_to_newsDetailsFragment, bundle)
    }

}