package info.mrprogrammer.admin_bio.Adapter


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import com.mrprogrammer.mrshop.ObjectHolder.ObjectHolder
import info.mrprogrammer.admin_bio.Model.UserDataModel
import info.mrprogrammer.admin_bio.R
import info.mrprogrammer.admin_bio.Utils.Companion.firebaseClearString

class UserListAdapter(private val context: Context, private val dataSet: List<UserDataModel>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roll: TextView = view.findViewById(R.id.rollNumber)
        val name: TextView = view.findViewById(R.id.name)
        val email: TextView = view.findViewById(R.id.email)
        val main: CardView = view.findViewById(R.id.main)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.roll.text = dataSet[position].roll
        holder.name.text = dataSet[position].name
        holder.email.text = dataSet[position].mail

        holder.main.setOnLongClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle("Deleted")
                .setPositiveButton("Yes") { _, _ ->
                    deleteCurrentId(dataSet[position].mail)
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            true
        }
    }

    private fun deleteCurrentId(mail: String) {
        val db = FirebaseDatabase.getInstance().getReference("UserData")
        val clearMail = mail.firebaseClearString().toString()
        db.child(clearMail).removeValue().addOnCompleteListener {
            ObjectHolder.MrToast().success(context as Activity,"Deleted Successfully")
        }
    }

    override fun getItemCount() = dataSet.size
}
