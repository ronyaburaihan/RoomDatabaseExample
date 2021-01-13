package com.techdoctorbd.roomdatabaseexample.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.techdoctorbd.roomdatabaseexample.R
import com.techdoctorbd.roomdatabaseexample.model.User

class ListAdapter() : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var userList = emptyList<User>()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNumber: TextView = itemView.findViewById(R.id.tv_number)
        val tvFistName: TextView = itemView.findViewById(R.id.tv_first_name)
        val tvLastName: TextView = itemView.findViewById(R.id.tv_last_name)
        val tvAge: TextView = itemView.findViewById(R.id.tv_age)
        val userItemLayout: ConstraintLayout = itemView.findViewById(R.id.user_item_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentUserItem = userList[position]
        holder.tvNumber.text = currentUserItem.id.toString()
        holder.tvFistName.text = currentUserItem.firstName
        holder.tvLastName.text = currentUserItem.lastName
        holder.tvAge.text = currentUserItem.age.toString()

        holder.userItemLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentUserItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(users: List<User>) {
        this.userList = users
        notifyDataSetChanged()
    }
}