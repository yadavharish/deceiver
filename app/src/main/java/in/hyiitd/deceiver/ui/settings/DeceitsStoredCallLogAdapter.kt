package `in`.hyiitd.deceiver.ui.settings

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import `in`.hyiitd.deceiver.DeceiverContentProvider
import `in`.hyiitd.deceiver.R
import `in`.hyiitd.deceiver.databinding.FragmentDeceitsBinding
import `in`.hyiitd.deceiver.databinding.ListItemFilterTabBinding
import `in`.hyiitd.deceiver.databinding.ListItemStoredCallLogBinding
import `in`.hyiitd.deceiver.util.DeceitsUtil
import `in`.hyiitd.deceiver.util.DeceitsUtil.supportedModes
import `in`.hyiitd.deceiver.util.DeceitsUtil.updateDeceitValuesByMode
import `in`.hyiitd.deceiver.util.PermsUtil.supportedPermissions

class DeceitsStoredCallLogAdapter(val callLogStrings: List<String>, val parentBinding: FragmentDeceitsBinding): RecyclerView.Adapter<DeceitsStoredCallLogAdapter.DeceitsStoredCallLogViewHolder>() {
    class DeceitsStoredCallLogViewHolder(val binding: ListItemStoredCallLogBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = callLogStrings.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DeceitsStoredCallLogViewHolder(ListItemStoredCallLogBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: DeceitsStoredCallLogViewHolder, position: Int) {
        with(holder){
            with(binding) {
                val ctx = itemView.context
                val names = callLogStrings[position].split("#*#*#")
                phoneNumber.text = names[0]
                callerName.text = names[1]
                callType.text = names[2]
                callDate.text = names[3]
                callDuration.text = names[4]

                btnDeleteCallLogDetails.setOnClickListener {
                    ctx.contentResolver.delete(DeceiverContentProvider.deceitCallLogsContentURI, "name = '${callLogStrings[position]}'", null)
                    parentBinding.rvStoredCallLogs.adapter = DeceitsStoredCallLogAdapter(DeceitsFragment.getCallLogsStrings(itemView.context), parentBinding)
                    notifyDataSetChanged()
                }

                btnEditCallLogDetails.setOnClickListener {
                    parentBinding.etCallLogPhoneNumber.setText(names[0])
                    parentBinding.etCallLogCallerName.setText(names[1])
                    parentBinding.etCallLogCallType.setText(names[2])
                    parentBinding.etCallLogCallDate.setText(names[3])
                    parentBinding.etCallLogCallDuration.setText(names[4])
                    ctx.contentResolver.delete(DeceiverContentProvider.deceitCallLogsContentURI, "name = '${callLogStrings[position]}'", null)
                    parentBinding.rvStoredCallLogs.adapter = DeceitsStoredCallLogAdapter(DeceitsFragment.getCallLogsStrings(itemView.context), parentBinding)
                    notifyDataSetChanged()
                }
            }
        }
    }
}