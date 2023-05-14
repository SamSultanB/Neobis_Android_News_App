package sam.sultan.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import sam.sultan.newsapp.databinding.FragmentMainBinding
import sam.sultan.newsapp.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {

    lateinit var binding: FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

}