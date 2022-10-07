package ru.netology.nmedia.activity

import PostViewModel
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.AndroidUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {viewModel.edit(post)}

            override fun onLike(post: Post) {viewModel.likeById(post.id)}

            override fun onRemove(post: Post) {viewModel.removeById(post.id)}

            override fun onShare(post: Post) { viewModel.shareById(post.id)}
        })
        ///подключаем адаптер и принимаем от него изменения
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        ///принимаем от viewModel изменение постов
        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.content) {
                group.visibility = View.VISIBLE
                contentEdit.text = post.content
                requestFocus()
                setText(post.content)
            }
        }
        ///обработчик кнопки сохранения поста
        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                ///вставляем в пост новые изменения
                viewModel.changeContent(text.toString())
                ///сохраняем изменения
                viewModel.save()

                setText("")
                clearFocus()
                ///убираем клавиатуру с экрана
                AndroidUtils.hideKeyboard(this)
            }
            with(binding.group){
                if(this.visibility == View.VISIBLE){
                    this.visibility = View.INVISIBLE
                }
            }
        }
        ///обработчик кнопки отменить изменение
        binding.deleteEdit.setOnClickListener{
            with(binding.group){
                if(this.visibility == View.VISIBLE){
                    this.visibility = View.INVISIBLE
                }
            }
            with(binding.content){
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.delete_content_edit),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(contentEdit.text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
    }
}