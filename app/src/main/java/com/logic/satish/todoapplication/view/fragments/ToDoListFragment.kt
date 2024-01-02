package com.logic.satish.todoapplication.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.gson.Gson
import com.logic.satish.todoapplication.R
import com.logic.satish.todoapplication.databinding.FragmentToDoListBinding
import com.logic.satish.todoapplication.db.TaskDatabase
import com.logic.satish.todoapplication.model.TaskEntity
import com.logic.satish.todoapplication.model.TaskListAdapter
import com.logic.satish.todoapplication.repository.TaskRepository
import com.logic.satish.todoapplication.viewmodel.TaskViewModel
import com.logic.satish.todoapplication.viewmodel.TaskViewModelFactory


class ToDoListFragment : Fragment() {

    private var _binding : FragmentToDoListBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var adapter:TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_to_do_list,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskDao = TaskDatabase.getDatabase(requireContext()).taskDao()
        val repository = TaskRepository(taskDao)
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(repository)).get(TaskViewModel::class.java)


        binding.fabAddTask.setOnClickListener{
            findNavController().navigate(R.id.action_toDoListFragment_to_addTaskFragment)
        }

        taskViewModel.tasks.observe(viewLifecycleOwner) {

            if (it.size == 0 || it.isEmpty() ) {
                binding.rvTaskList.visibility = View.GONE
                binding.tvEmptyList.visibility=View.VISIBLE
            } else {
                binding.rvTaskList.visibility = View.VISIBLE
                binding.tvEmptyList.visibility=View.GONE
                adapter.submitList(it)

                val updated = arguments?.getBoolean("update")
                if (updated == true){
                    binding.rvTaskList.getLayoutManager()
                        ?.smoothScrollToPosition(binding.rvTaskList,null, it.size -1);
                }

            }
        }

        binding.rvTaskList.layoutManager = LinearLayoutManager(activity)
        adapter = TaskListAdapter{ tasks->
            navigateToTaskDetails(tasks)
        }
        binding.rvTaskList.adapter = adapter
    }

    private fun navigateToTaskDetails(task:TaskEntity){

        val bundle = Bundle()
        bundle.putString("task", Gson().toJson(task))
        findNavController().navigate(R.id.action_toDoListFragment_to_taskDetailsFragment,bundle)

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}