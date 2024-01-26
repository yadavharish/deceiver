package `in`.hyiitd.deceiver.ui.deceiver

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import `in`.hyiitd.deceiver.R
import `in`.hyiitd.deceiver.databinding.FragmentDeceiverBinding
import `in`.hyiitd.deceiver.databinding.ListItemFilterTabBinding
import `in`.hyiitd.deceiver.util.PkgsUtil.currentAppsFilter
import `in`.hyiitd.deceiver.util.PkgsUtil.filterAppMenu
import `in`.hyiitd.deceiver.util.PkgsUtil.filterInstalledPackages
import `in`.hyiitd.deceiver.util.PkgsUtil.filteredPackages

class FilterAppAdapter(val parentBinding: FragmentDeceiverBinding): RecyclerView.Adapter<FilterAppAdapter.FilterAppViewHolder>() {
    class FilterAppViewHolder(val binding: ListItemFilterTabBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = filterAppMenu.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilterAppViewHolder(ListItemFilterTabBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FilterAppViewHolder, position: Int) {
        with(holder){
            with(filterAppMenu[position]) {
                with(binding) {
                    tvName.text = name
                    val ctx = itemView.context
                    itemView.backgroundTintList = ctx.resources.getColorStateList(if(position == currentAppsFilter) R.color.theme1Green2 else R.color.theme1White, ctx.theme);
                    tvName.setTextColor(ContextCompat.getColor(ctx, if(position == currentAppsFilter) R.color.theme1Orange else R.color.theme1Green2))
                    itemView.setOnClickListener {
                        currentAppsFilter = value
                        filterInstalledPackages()
                        parentBinding.rvApps.adapter = AppAdapter(filteredPackages)
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }

//    fun updateUIs() {
//        bin
//        when(currentAppsFilter)
//    }
}