package com.uz.kattabozor.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.uz.kattabozor.data.models.Post
import com.uz.kattabozor.databinding.AttributeItemBinding
import com.uz.kattabozor.databinding.DetailsFragmentBinding
import com.uz.kattabozor.home.load

class DetailsFragment(private val post: Post, val manager: FragmentManager) : Fragment() {

    var binding: DetailsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater)
        binding?.apply {
            backButton.setOnClickListener {
                manager.popBackStack()
            }
            photoView.load(post.image!!.url)
            nameView.text = post.name
            brandView.text = post.brand
            category.text = post.category
            merchantText.text = post.merchant
            attributes.apply {
                post.attributes?.forEach {
                    AttributeItemBinding.inflate(inflater, null, false).apply {
                        name.text = it.name
                        value.text = it.value
                        addView(
                            root,
                            LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            ).apply {
                                marginStart = 18
                            })
                    }
                }
            }
        }
        return binding!!.root
    }
}