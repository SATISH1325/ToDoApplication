package com.logic.satish.todoapplication.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.logic.satish.todoapplication.R
import com.logic.satish.todoapplication.databinding.FragmentAddTaskBinding
import com.logic.satish.todoapplication.db.TaskDatabase
import com.logic.satish.todoapplication.repository.TaskRepository
import com.logic.satish.todoapplication.viewmodel.TaskViewModel
import com.logic.satish.todoapplication.viewmodel.TaskViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddTaskFragment : Fragment() {

    private var _binding : FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_task,container,false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskDao = TaskDatabase.getDatabase(requireContext()).taskDao()
        val repository = TaskRepository(taskDao)
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(repository)).get(TaskViewModel::class.java)

        binding.btnAddTask.setOnClickListener {

        val taskTitle = binding.etTaskTitle.text.toString()
        val taskDescription = binding.etTaskDescription.text.toString()

            if (taskTitle.isEmpty() || taskDescription.isEmpty()){
                Toast.makeText(context,"Task Title or Description must be not empty...!",Toast.LENGTH_SHORT).show()
            } else {

                val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss",Locale.getDefault())
                val current = formatter.format(Date())

                taskViewModel.addTask(
                    taskTitle = taskTitle,
                    taskDescription = taskDescription,
                    taskDateTime = current
                )

                val bundle = Bundle()
                bundle.putBoolean("update", true)
              findNavController().navigate(R.id.action_addTaskFragment_to_toDoListFragment,bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}