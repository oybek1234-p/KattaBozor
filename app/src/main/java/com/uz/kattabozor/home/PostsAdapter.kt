package com.uz.kattabozor.home

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.math.MathUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uz.kattabozor.data.models.Post
import com.uz.kattabozor.databinding.PostItemBinding

class PostsAdapter(val click: (pos: Int, post: Post) -> Unit) :
    RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    private lateinit var inflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        inflater = LayoutInflater.from(recyclerView.context)
    }

    class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)

    var posts: ArrayList<Post>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return posts?.size ?: 0
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.apply {
            binding.apply {
                val model = posts!![position]
                val image = model.image!!
                imageView.apply {
                    val params = layoutParams as ConstraintLayout.LayoutParams
                    val ratio =
                        MathUtils.clamp(image.height.toFloat() / image.width.toFloat(), 1f, 1.5f)
                    params.dimensionRatio = "1:$ratio"
                    layoutParams = params
                    load(image.url)
                }
                nameView.text = model.name
                brandView.text = model.brand
                category.text = model.merchant
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = PostItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(view).apply {
            binding.root.setOnClickListener {
                click.invoke(layoutPosition, posts!![layoutPosition])
            }
        }
    }
}

fun ImageView.load(url: String) {
    val glide = Glide.with(this)
    glide.load(url).transition(DrawableTransitionOptions.withCrossFade()).into(this)
}

const val padding = 30

var postDecoration = object : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val params = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
        outRect.apply {
            if (params.spanIndex == 0) {
                left = padding
                right = padding / 2
            } else {
                left = padding / 2
                right = padding
            }
            top = padding
        }
    }
}