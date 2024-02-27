package info.mrprogrammer.admin_bio.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import info.mrprogrammer.admin_bio.Adapter.UserListAdapter
import info.mrprogrammer.admin_bio.Model.UserDataModel
import info.mrprogrammer.admin_bio.R

class ListOfUser : Fragment() {
    lateinit var root:View;
    lateinit var adapter:UserListAdapter
    private var listOfData = mutableListOf<UserDataModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        root = inflater.inflate(R.layout.userlist_fragment, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.userList)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = UserListAdapter(requireContext(), listOfData)
        recyclerView.adapter = adapter
        getListOfUser()
        return root
    }

    private fun getListOfUser() {
        val db = FirebaseDatabase.getInstance().getReference("UserData")
        db.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfData.clear()
                snapshot.children.forEach {
                    it.getValue(UserDataModel::class.java)?.let { it1 -> listOfData.add(it1) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}