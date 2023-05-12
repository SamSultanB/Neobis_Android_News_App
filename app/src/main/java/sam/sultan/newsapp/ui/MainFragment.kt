package sam.sultan.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sam.sultan.newsapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

}