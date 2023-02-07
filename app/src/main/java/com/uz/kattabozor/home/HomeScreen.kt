package com.uz.kattabozor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.uz.kattabozor.R
import com.uz.kattabozor.data.Repository
import com.uz.kattabozor.databinding.HomeScreenBinding
import com.uz.kattabozor.details.DetailsFragment

class HomeScreen : Fragment() {
    var binding: HomeScreenBinding? = null
    var adapterM: PostsAdapter? = null
    lateinit var repository: Repository

    private var loading = false
        set(value) {
            field = value
            binding?.progressBar?.visibility = if (value) View.VISIBLE else View.INVISIBLE
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeScreenBinding.inflate(inflater)
        repository = Repository()
        binding?.apply {
            recyclerView.apply {
                adapterM = PostsAdapter { _, post ->
                    val details = DetailsFragment(post,parentFragmentManager)
                    parentFragmentManager.beginTransaction().apply {
                        replace(R.id.container, details)
                        addToBackStack(null)
                        commit()
                    }
                }
                adapter = adapterM
                addItemDecoration(postDecoration)
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                loading = true
                repository.getPosts { post, error ->
                    text.text = error
                    loading = false
                    post?.posts?.let {
                        val list = ArrayList(it.toMutableList())
                        adapterM?.posts = list
                    }
                }
            }
        }
        return binding!!.root
    }
}