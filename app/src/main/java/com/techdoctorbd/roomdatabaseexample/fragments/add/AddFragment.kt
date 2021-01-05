package com.techdoctorbd.roomdatabaseexample.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.techdoctorbd.roomdatabaseexample.R
import com.techdoctorbd.roomdatabaseexample.UserViewModel
import com.techdoctorbd.roomdatabaseexample.data.User
import com.techdoctorbd.roomdatabaseexample.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.buttonAdd.setOnClickListener {
            insertDataToDataToDatabase()
        }
    }

    private fun insertDataToDataToDatabase() {
        val firstName = binding.edFirstName.text.toString()
        val lastName = binding.edLastName.text.toString()
        val age = binding.edAge.text

        if (inputCheck(firstName, lastName, age)) {
            //crate user object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            // Add data to database
            viewModel.addUser(user)
            Toast.makeText(requireContext(), "Data successfully added", Toast.LENGTH_SHORT).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}