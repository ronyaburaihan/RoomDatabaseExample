package com.techdoctorbd.roomdatabaseexample.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.techdoctorbd.roomdatabaseexample.R
import com.techdoctorbd.roomdatabaseexample.viewmodel.UserViewModel
import com.techdoctorbd.roomdatabaseexample.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButtonList.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val adapter = ListAdapter()

        binding.recyclerViewList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        //for UserViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.item_delete) deleteAllUser()

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {

        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteAll()
            Toast.makeText(requireContext(), "All User has been deleted", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") { _, _ ->
        }
        builder.setTitle("Delete everything ?")
        builder.setMessage("Are you sure to delete all user?")
        builder.create().show()
    }
}