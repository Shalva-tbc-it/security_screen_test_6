package com.example.securityscreen.presentation.security_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.securityscreen.databinding.RecyclerKeyboardBinding

class KeyboardRecyclerAdapter : RecyclerView.Adapter<KeyboardRecyclerAdapter.KeyboardViewHolder>() {

    inner class KeyboardViewHolder(private val binding: RecyclerKeyboardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyboardViewHolder {
        return KeyboardViewHolder(RecyclerKeyboardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
            )
        )
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: KeyboardViewHolder, position: Int) {
        holder.bind()
    }
}