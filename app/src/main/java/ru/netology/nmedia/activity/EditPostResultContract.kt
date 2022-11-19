package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.dto.Post

class EditPostResultContract() : ActivityResultContract<Post, Post>(){

    override fun createIntent(context: Context, input: Post): Intent {
        val intent = Intent(context, EditPostActivity::class.java)
        intent.putExtra("id", input.id)
        intent.putExtra("author", input.author)
        intent.putExtra("content", input.content)
        intent.putExtra("published", input.published)
        intent.putExtra("likedByMe", input.likedByMe)
        intent.putExtra("likes", input.likes)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Post {
        val post = Post(
            id = intent?.getLongExtra(Intent.EXTRA_TEXT,0),
            author = intent?.getStringExtra(Intent.EXTRA_TEXT).toString(),
            content = intent?.getStringExtra(Intent.EXTRA_TEXT).toString(),
            published = intent?.getStringExtra(Intent.EXTRA_TEXT).toString(),
            likedByMe = intent?.getBooleanExtra(Intent.EXTRA_TEXT, false),
            likes = intent?.getIntExtra(Intent.EXTRA_TEXT, 0)
        )
        println(post)
        return post
    }
}

///val id: Long,
///val author: String,
///val content: String,
///val published: String,
///val likedByMe: Boolean,
///val likes: Int = 0,