package sam.sultan.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.navArgs
import sam.sultan.newsapp.databinding.FragmentMainBinding
import sam.sultan.newsapp.databinding.FragmentNewsDetailsBinding
import sam.sultan.newsapp.models.Article

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = arguments?.getSerializable("article") as? Article

        if (article == null){
            Toast.makeText(requireContext(), "Can't find", Toast.LENGTH_SHORT).show()
        }else{
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
        }

    }

}