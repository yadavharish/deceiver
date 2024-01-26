package `in`.hyiitd.deceiver.ui.deceiver

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import `in`.hyiitd.deceiver.R
import `in`.hyiitd.deceiver.databinding.FragmentDeceiverBinding
import `in`.hyiitd.deceiver.databinding.ListItemFilterTabBinding
import `in`.hyiitd.deceiver.util.PkgsUtil.currentAppsSortOrder
import `in`.hyiitd.deceiver.util.PkgsUtil.filteredPackages
import `in`.hyiitd.deceiver.util.PkgsUtil.sortFilteredPackages
import `in`.hyiitd.deceiver.util.PkgsUtil.sortOrderAppMenu

class SortAppOrderAdapter(val parentBinding: FragmentDeceiverBinding): RecyclerView.Adapter<SortAppOrderAdapter.SortAppOrderViewHolder>() {
    class SortAppOrderViewHolder(val binding: ListItemFilterTabBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = sortOrderAppMenu.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SortAppOrderViewHolder(ListItemFilterTabBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SortAppOrderViewHolder, position: Int) {
        with(holder){
            with(sortOrderAppMenu[position]) {
                with(binding) {
                    tvName.text = name
                    val ctx = itemView.context
                    itemView.backgroundTintList = ctx.resources.getColorStateList(if(position == currentAppsSortOrder) R.color.theme1Green2 else R.color.theme1White, ctx.theme);
                    tvName.setTextColor(ContextCompat.getColor(ctx, if(position == currentAppsSortOrder) R.color.theme1Orange else R.color.theme1Green2))
                    itemView.setOnClickListener {
                        currentAppsSortOrder = value
                        sortFilteredPackages()
                        parentBinding.rvApps.adapter = AppAdapter(filteredPackages)
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }
}