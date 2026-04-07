package com.example.week03_taro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.week03_taro.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private var selectedTab: ShopTab = ShopTab.ALL

    enum class ShopTab {
        ALL, TOPS, SALE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedTab = savedInstanceState?.getString("selected_tab")
            ?.let { ShopTab.valueOf(it) }
            ?: ShopTab.ALL

        binding.tabAllContainer.setOnClickListener { selectTab(ShopTab.ALL) }
        binding.tabTopsContainer.setOnClickListener { selectTab(ShopTab.TOPS) }
        binding.tabSaleContainer.setOnClickListener { selectTab(ShopTab.SALE) }

        selectTab(selectedTab)
    }

    private fun selectTab(tab: ShopTab) {
        selectedTab = tab
        updateTabUi(tab)

        val fragment = when (tab) {
            ShopTab.ALL -> ShopAllFragment()
            ShopTab.TOPS -> ShopTopsFragment()
            ShopTab.SALE -> ShopSaleFragment()
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.shop_tab_fragment_container, fragment)
            .commit()
    }

    private fun updateTabUi(tab: ShopTab) {
        val black = ContextCompat.getColor(requireContext(), android.R.color.black)
        val gray = 0xFF8E8E8E.toInt()

        binding.tvTabAll.setTextColor(if (tab == ShopTab.ALL) black else gray)
        binding.tvTabTops.setTextColor(if (tab == ShopTab.TOPS) black else gray)
        binding.tvTabSale.setTextColor(if (tab == ShopTab.SALE) black else gray)

        binding.viewTabAllIndicator.visibility = if (tab == ShopTab.ALL) View.VISIBLE else View.INVISIBLE
        binding.viewTabTopsIndicator.visibility = if (tab == ShopTab.TOPS) View.VISIBLE else View.INVISIBLE
        binding.viewTabSaleIndicator.visibility = if (tab == ShopTab.SALE) View.VISIBLE else View.INVISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("selected_tab", selectedTab.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}