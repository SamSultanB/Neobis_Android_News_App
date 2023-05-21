package sam.sultan.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import sam.sultan.newsapp.R
import sam.sultan.newsapp.RecyclerViewAdapter
import sam.sultan.newsapp.database.NewsDataBase
import sam.sultan.newsapp.databinding.FragmentMainBinding
import sam.sultan.newsapp.models.Article
import sam.sultan.newsapp.repository.NewsRepository
import sam.sultan.newsapp.utils.Resource
import sam.sultan.newsapp.viewModel.NewsViewModel
import sam.sultan.newsapp.viewModel.ViewModelFactory

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpRV()

        viewModel.news.observe(viewLifecycleOwner, Observer{response ->
            showProgressBar()
            if (response is Resource.Success){
                hideProgressBar()
                response.data?.let {
                    adapter.setNewsist(it.articles)
                }
            }else if (response is Resource.Error){
                Toast.makeText(requireContext(),response.message, Toast.LENGTH_SHORT).show()
            }
        })

        binding.refreshButton.setOnClickListener {
            refreshNews()
        }

        binding.savedButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_savedNewsFragment)
        }

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean{
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchNews(it)}
                return true
            }

        })

        adapter.clickToDetails = { clickToDetails(it) }

    }

    private fun setUpRV(){
        adapter = RecyclerViewAdapter()
        binding.mainRV.layoutManager = LinearLayoutManager(context)
        binding.mainRV.adapter = adapter
    }

    private fun setUpViewModel(){
        val db = NewsDataBase.getDatabase(requireContext()).getNewsDao()
        val repository = NewsRepository(db)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(NewsViewModel::class.java)
    }

    private fun clickToDetails(article: Article){
        val bundle = Bundle()
        bundle.putSerializable("article", article)
        findNavController().navigate(R.id.action_mainFragment_to_newsDetailsFragment, bundle)
    }

    private fun refreshNews(){
        adapter.setNewsist(listOf())
        viewModel.getNews()
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }

}