package com.satya.exerciseweek6_160421048.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satya.exerciseweek6_160421048.databinding.CakeListItemBinding
import com.satya.exerciseweek6_160421048.model.Cake
import com.squareup.picasso.Picasso

class CakeListAdapter(val cakeList:ArrayList<Cake>):RecyclerView.Adapter<CakeListAdapter.CakeViewHolder>() {

    class CakeViewHolder(var binding: CakeListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CakeViewHolder {
        val binding = CakeListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CakeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        holder.binding.txtNama.text = cakeList[position].name
        holder.binding.txtIngridient.text = cakeList[position].ingredients
        holder.binding.txtDesc.text = cakeList[position].desc

        val url = cakeList[position].images
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        Picasso.get().load(url).into(holder.binding.imgCake)


    }

    override fun getItemCount(): Int {
        return cakeList.size
    }

    fun updateCakeList(newCakelist: ArrayList<Cake>){
        cakeList.clear()
        cakeList.addAll(newCakelist)
        notifyDataSetChanged()
    }

}