package com.logic.satish.todoapplication.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.logic.satish.todoapplication.R
import com.logic.satish.todoapplication.databinding.FragmentAddTaskBinding
import com.logic.satish.todoapplication.databinding.FragmentTaskDetailsBinding
import com.logic.satish.todoapplication.db.TaskDatabase
import com.logic.satish.todoapplication.model.TaskEntity
import com.logic.satish.todoapplication.repository.TaskRepository
import com.logic.satish.todoapplication.viewmodel.TaskViewModel
import com.logic.satish.todoapplication.viewmodel.TaskViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class TaskDetailsFragment : Fragment() {

    private var _binding : FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!
    private var task : TaskEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_task_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsontask = arguments?.getString("task")
        if (jsontask != null){
            task = Gson().fromJson(jsontask,TaskEntity::class.java)
        }

        binding.task = task
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}