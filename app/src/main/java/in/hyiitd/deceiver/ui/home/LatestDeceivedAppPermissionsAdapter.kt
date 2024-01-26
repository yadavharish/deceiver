package `in`.hyiitd.deceiver.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.hyiitd.deceiver.database.PermissionInfo
import `in`.hyiitd.deceiver.databinding.ListItemLatestDeceivedAppPermissionBinding
import `in`.hyiitd.deceiver.util.PermsUtil.getPermissionIcon

class LatestDeceivedAppPermissionsAdapter(val permissions: List<PermissionInfo>): RecyclerView.Adapter<LatestDeceivedAppPermissionsAdapter.LatestDeceivedAppPermissionsViewHolder>() {
    class LatestDeceivedAppPermissionsViewHolder(val binding: ListItemLatestDeceivedAppPermissionBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = permissions.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LatestDeceivedAppPermissionsViewHolder(ListItemLatestDeceivedAppPermissionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: LatestDeceivedAppPermissionsViewHolder, position: Int) {
        with(holder) {
            with(permissions[position]) {
                with(binding) {
                    tvPermissionName.text = permissionName
                    ivPermissionIcon.setImageDrawable(getPermissionIcon(permissionName, true, itemView.context))
                }
            }
        }
    }
}