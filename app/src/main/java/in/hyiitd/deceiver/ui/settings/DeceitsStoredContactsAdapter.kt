package `in`.hyiitd.deceiver.ui.settings

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import `in`.hyiitd.deceiver.DeceiverContentProvider
import `in`.hyiitd.deceiver.R
import `in`.hyiitd.deceiver.databinding.FragmentDeceitsBinding
import `in`.hyiitd.deceiver.databinding.ListItemFilterTabBinding
import `in`.hyiitd.deceiver.databinding.ListItemStoredCallLogBinding
import `in`.hyiitd.deceiver.databinding.ListItemStoredContactBinding
import `in`.hyiitd.deceiver.util.DeceitsUtil
import `in`.hyiitd.deceiver.util.DeceitsUtil.supportedModes
import `in`.hyiitd.deceiver.util.DeceitsUtil.updateDeceitValuesByMode
import `in`.hyiitd.deceiver.util.PermsUtil.supportedPermissions

class DeceitsStoredContactsAdapter(val contactStrings: List<String>, val parentBinding: FragmentDeceitsBinding): RecyclerView.Adapter<DeceitsStoredContactsAdapter.DeceitsStoredContactsViewHolder>() {
    class DeceitsStoredContactsViewHolder(val binding: ListItemStoredContactBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = contactStrings.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DeceitsStoredContactsViewHolder(ListItemStoredContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: DeceitsStoredContactsViewHolder, position: Int) {
        with(holder){
            with(binding) {
                val ctx = itemView.context
                val names = contactStrings[position].split("#*#*#")
                phoneNumber.text = names[0]
                contactName.text = names[1]

                btnDeleteContactsDetails.setOnClickListener {
                    ctx.contentResolver.delete(DeceiverContentProvider.deceitContactsContentURI, "name = '${contactStrings[position]}'", null)
                    parentBinding.rvStoredContacts.adapter = DeceitsStoredContactsAdapter(DeceitsFragment.getContactsStrings(itemView.context), parentBinding)
                    notifyDataSetChanged()
                }

                btnEditContactsDetails.setOnClickListener {
                    parentBinding.etContactsPhoneNumber.setText(names[0])
                    parentBinding.etContactsContactName.setText(names[1])
                    ctx.contentResolver.delete(DeceiverContentProvider.deceitContactsContentURI, "name = '${contactStrings[position]}'", null)
                    parentBinding.rvStoredContacts.adapter = DeceitsStoredContactsAdapter(DeceitsFragment.getContactsStrings(itemView.context), parentBinding)
                    notifyDataSetChanged()
                }
            }
        }
    }
}