package ru.netology.nmedia.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    lateinit var context : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.edit(result)
        }

        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                editPostLauncher.launch(post)
                viewModel.edit(post)
            }

            override fun onLike(post: Post) {
                post.id.let {
                    if (it != null) {
                        viewModel.likeById(it)
                    }
                }
            }

            override fun onRemove(post: Post) {
                post.id.let {
                    if(it != null){
                        viewModel.removeById(it)
                    }
                }
            }

            override fun onShare(post: Post) {
                intentA(post)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.fab.setOnClickListener {
            newPostLauncher.launch()
        }
    }

    //нужно для красоты кода
    fun intentA(post: Post) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, post.content)
            type = "text/plain"
        }

        val shareIntent =
            Intent.createChooser(intent, getString(R.string.chooser_share_post))
        startActivity(shareIntent)
    }
}