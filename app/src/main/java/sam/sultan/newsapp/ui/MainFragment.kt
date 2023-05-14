package sam.sultan.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sam.sultan.newsapp.MainActivity
import sam.sultan.newsapp.RecyclerViewAdapter
import sam.sultan.newsapp.api.RetrofitInstance
import sam.sultan.newsapp.database.NewsDataBase
import sam.sultan.newsapp.databinding.FragmentMainBinding
import sam.sultan.newsapp.utils.Resource
import sam.sultan.newsapp.viewModel.NewsViewModel

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private var viewModel = NewsViewModel()

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
        adapter = RecyclerViewAdapter()
        binding.mainRV.layoutManager = LinearLayoutManager(context)
        binding.mainRV.adapter = adapter

        viewModel.getNews()
        viewModel.news.observe(viewLifecycleOwner, Observer{response ->
            response.body()?.let {
                adapter.setNewsist(it.articles)
            }
        })

    }

}