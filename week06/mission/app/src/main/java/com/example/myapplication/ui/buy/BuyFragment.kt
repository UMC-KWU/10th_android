package com.example.myapplication.ui.buy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.model.Product
import com.example.myapplication.databinding.FragmentBuyBinding
import com.example.myapplication.ui.detail.ProductDetailActivity
import com.example.myapplication.ui.wishlist.WishlistViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyFragment : Fragment() {
    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BuyViewModel by viewModels()
    private val wishlistViewModel: WishlistViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
        setupRecyclerView(viewModel.products)
    }

    override fun onResume() {
        super.onResume()
        if (binding.rvProducts.visibility == View.VISIBLE) {
            setupRecyclerView(viewModel.products)
        }
    }

    private fun setupTabs() {
        listOf("전체", "Tops & T-Shirts", "Shoes").forEach {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(it))
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.rvProducts.visibility = View.VISIBLE
                        binding.tabFragmentContainer.visibility = View.GONE
                        setupRecyclerView(viewModel.products)
                    }
                    1 -> showTabFragment(TopsFragment())
                    2 -> showTabFragment(ShoesFragment())
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun showTabFragment(fragment: Fragment) {
        binding.rvProducts.visibility = View.GONE
        binding.tabFragmentContainer.visibility = View.VISIBLE
        childFragmentManager.beginTransaction()
            .replace(R.id.tab_fragment_container, fragment)
            .commit()
    }

    private fun setupRecyclerView(products: List<Product>) {
        binding.rvProducts.layoutManager = GridLayoutManager(context, 2)
        binding.rvProducts.adapter = BuyProductAdapter(
            products = products,
            isWishlisted = { wishlistViewModel.isWishlisted(it) },
            onWishlistToggle = { wishlistViewModel.toggle(it) }
        ) { product ->
            startActivity(Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("image", product.image)
                putExtra("name", product.name)
                putExtra("category", product.category)
                putExtra("colors", product.colors)
                putExtra("price", product.price)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
