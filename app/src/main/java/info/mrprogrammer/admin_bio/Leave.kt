package info.mrprogrammer.admin_bio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import info.mrprogrammer.admin_bio.Adapter.LeaveListAdapter
import info.mrprogrammer.admin_bio.Adapter.UserListAdapter
import info.mrprogrammer.admin_bio.Model.LeaveDataMode
import info.mrprogrammer.admin_bio.Model.UserDataModel

class Leave : AppCompatActivity() {
    lateinit var adapter: LeaveListAdapter
    private var listOfData = mutableListOf<LeaveDataMode>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave)
        val recyclerView: RecyclerView = findViewById(R.id.userList)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = LeaveListAdapter(this, listOfData)
        recyclerView.adapter = adapter
        getListOfUser()
    }

    private fun getListOfUser() {
        val db = FirebaseDatabase.getInstance().getReference("leave")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfData.clear()

                snapshot.children.forEach {out->
                    out.children.forEach {
                        val data = it.getValue(LeaveDataMode::class.java)
                        data?.outerKey =  out.key.toString()
                        data?.key = it.key.toString()
                        if (data != null) {
                            listOfData.add(data)
                        }
                    }
                }

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}