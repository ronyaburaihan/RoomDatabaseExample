package com.techdoctorbd.roomdatabaseexample.fragments.update

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.techdoctorbd.roomdatabaseexample.R
import com.techdoctorbd.roomdatabaseexample.databinding.FragmentUpdateBinding
import com.techdoctorbd.roomdatabaseexample.model.User
import com.techdoctorbd.roomdatabaseexample.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.edFirstNameUpdate.setText(args.currentUser.firstName)
        binding.edLastNameUpdate.setText(args.currentUser.lastName)
        binding.edAgeUpdate.setText(args.currentUser.age.toString())

        binding.buttonUpdate.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        val firstName = binding.edFirstNameUpdate.text.toString()
        val lastName = binding.edLastNameUpdate.text.toString()
        val age = binding.edAgeUpdate.text

        if (inputCheck(firstName, lastName, age)) {
            //crate user object
            val user =
                User(args.currentUser.id, firstName, lastName, Integer.parseInt(age.toString()))
            // Add data to database
            viewModel.updateUser(user)
            Toast.makeText(requireContext(), "Data successfully updated", Toast.LENGTH_SHORT).show()
            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.item_delete) deleteUser()

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully deleted ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No") { _, _ ->
        }
        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Are you sure to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}