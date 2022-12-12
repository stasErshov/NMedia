package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(inflater, container, false)
        arguments?.textArg.let {
            binding.edit.setText(it)
        }

        val viewModel by viewModels<PostViewModel>(ownerProducer = ::requireParentFragment)

        binding.edit.requestFocus()
        binding.ok.setOnClickListener {
            val text = binding.edit.text.toString()
            viewModel.changeContent(text)
            viewModel.save()
            findNavController().navigateUp()
        }
        return binding.root
    }

    companion object {
        var Bundle.textArg by StringArg
    }
}