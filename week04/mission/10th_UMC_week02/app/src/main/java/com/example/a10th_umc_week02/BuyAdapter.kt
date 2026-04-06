package com.example.a10th_umc_week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.a10th_umc_week02.databinding.ItemBuyBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BuyAdapter(
    private var productList: MutableList<BuyData>,
    private val onVisitClicked: (BuyData) -> Unit
) : RecyclerView.Adapter<BuyAdapter.BuyViewHolder>() {

    class BuyViewHolder(val binding: ItemBuyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(home: BuyData) {
            binding.tvProduct3.setImageResource(home.image)
            binding.tvProductName3.text = home.name
            binding.tvProductPrice3.text = home.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val binding = ItemBuyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BuyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
        val product = productList[position]
        val dataStoreModule = DataStoreModule()
        holder.bind(product)

        (holder.itemView.context as? LifecycleOwner)?.lifecycleScope?.launch {
            dataStoreModule.getName(holder.itemView.context).collect { currentWishList ->
                val isFavorite = currentWishList.any { it.name == product.name }
                if (isFavorite) {
                    holder.binding.heart.setImageResource(R.drawable.ic_redheart)
                } else {
                    holder.binding.heart.setImageResource(R.drawable.ic_heart)
                }
            }
        }

        holder.binding.heart.setOnClickListener {
            (holder.itemView.context as? LifecycleOwner)?.lifecycleScope?.launch {
                val currentWishList = dataStoreModule.getName(holder.itemView.context).first().toMutableList()

                val existingItem = currentWishList.find { it.name == product.name }

                if (existingItem != null) {
                    currentWishList.removeIf { it.name == product.name }
                } else {
                    currentWishList.add(product)
                }
                dataStoreModule.saveName(holder.itemView.context, currentWishList)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}