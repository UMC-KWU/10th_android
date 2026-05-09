package com.example.myapplication.ui.buy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Product
import com.example.myapplication.databinding.ItemBuyProductBinding

class BuyProductAdapter(
    private val products: List<Product>,
    private val isWishlisted: (Product) -> Boolean,
    private val onWishlistToggle: (Product) -> Unit,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<BuyProductAdapter.BuyProductViewHolder>() {

    inner class BuyProductViewHolder(private val binding: ItemBuyProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.ivProduct.setImageResource(product.image)
            binding.tvName.text = product.name
            binding.tvCategory.text = product.category
            binding.tvColors.text = product.colors
            binding.tvPrice.text = product.price
            binding.tvBestSeller.visibility = if (product.isBestSeller) View.VISIBLE else View.GONE
            updateWishlistIcon(product)

            binding.btnWishlist.setOnClickListener {
                onWishlistToggle(product)
                updateWishlistIcon(product)
            }
            binding.root.setOnClickListener { onItemClick(product) }
        }

        private fun updateWishlistIcon(product: Product) {
            val icon = if (isWishlisted(product)) R.drawable.heartstraight_filled else R.drawable.heartstraight
            binding.btnWishlist.setImageResource(icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyProductViewHolder {
        val binding = ItemBuyProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size
}
