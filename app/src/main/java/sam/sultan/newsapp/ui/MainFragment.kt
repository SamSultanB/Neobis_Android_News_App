package sam.sultan.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import sam.sultan.newsapp.R
import sam.sultan.newsapp.RecyclerViewAdapter
import sam.sultan.newsapp.databinding.FragmentMainBinding
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
        showProgressBar()

        viewModel.news.observe(viewLifecycleOwner, Observer{response ->
            if(response.isSuccessful){
                response.body()?.let {
                    adapter.setNewsist(it.articles)
                }
                hideProgressBar()
            }else{
                Toast.makeText(requireContext(), "Connection error!", Toast.LENGTH_SHORT).show()
            }
        })

        //click to details page
        adapter.clickToDetails = {
            if(it != null){
                val bundle = Bundle()
                bundle.putSerializable("article", it)
                findNavController().navigate(R.id.action_mainFragment_to_newsDetailsFragment, bundle)
            }else{
                Toast.makeText(requireContext(), "Can't find it", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }

}