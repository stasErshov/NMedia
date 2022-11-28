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
        intent.putExtra("video", input.videoUri)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Post {
        val result : Post = Post(
            intent?.getLongExtra("id", 0),
            intent?.getStringExtra("author").toString(),
            intent?.getStringExtra("content").toString(),
            intent?.getStringExtra("published").toString(),
            intent?.getBooleanExtra("likedByMe", false),
            intent?.getIntExtra("likes", 0),
            intent?.getStringExtra("video").toString()
        )
        if (resultCode == Activity.RESULT_OK) {
            return result
        } else {
            return result
        }
    }
}