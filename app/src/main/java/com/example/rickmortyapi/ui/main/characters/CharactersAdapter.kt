package com.example.rickmortyapi.ui.main.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmortyapi.R
import com.example.rickmortyapi.databinding.ItemCharacterBinding
import com.example.rickmortyapi.models.characters.Result

class CharactersPagedListAdapter constructor(
    private val onClick: (com.example.rickmortyapi.models.characters.Result) -> Unit
) :
    PagingDataAdapter<Result, MyViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            characterName.text = item?.name
            characterStatus.text = "${item?.status} - ${item?.species}"
            when (item?.status) {
                "Alive" -> characterStatusView.setBackgroundResource(R.color.green)
                "Dead" -> characterStatusView.setBackgroundResource(R.color.red)
                else -> characterStatusView.setBackgroundResource(R.color.grey)
            }
            characterLastLocationValue.text = "${item?.location?.name}"
            item?.let {
                Glide
                    .with(characterImage)
                    .load(it.image)
                    .into(characterImage)
            }
            root.setOnClickListener {
                item?.let {
                    onClick(item)
                }
            }
        }
    }
}

class MyViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
        oldItem.name == newItem.name && oldItem.species == newItem.species
}