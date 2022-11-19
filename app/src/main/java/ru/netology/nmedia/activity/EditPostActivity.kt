package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityEditPostBinding
import ru.netology.nmedia.dto.Post

class EditPostActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent()
        val input = Post(getIntent().getLongExtra("id",0),
            getIntent().getStringExtra("author").toString(),
            getIntent().getStringExtra("content").toString(),
            getIntent().getStringExtra("published").toString(),
            getIntent().getBooleanExtra("likedByMe", false),
            getIntent().getIntExtra("likes", 0)
        )
        binding.edit.setText(input.content)
        binding.ok.setOnClickListener {
            intent.putExtra(Intent.EXTRA_TEXT, input.id)
            intent.putExtra(Intent.EXTRA_TEXT, input.author)
            intent.putExtra(Intent.EXTRA_TEXT, binding.edit.text.toString())
            intent.putExtra(Intent.EXTRA_TEXT, input.published)
            intent.putExtra(Intent.EXTRA_TEXT, input.likedByMe)
            intent.putExtra(Intent.EXTRA_TEXT, input.likes)
            finish()
        }
    }
}

///val id: Long,
///val author: String,
///val content: String,
///val published: String,
///val likedByMe: Boolean,
///val likes: Int = 0,