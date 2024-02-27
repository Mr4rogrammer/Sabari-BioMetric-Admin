package com.mrprogrammer.attendance.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import com.mrprogrammer.Utils.Widgets.ProgressButton
import com.mrprogrammer.mrshop.ObjectHolder.ObjectHolder
import info.mrprogrammer.admin_bio.R
import info.mrprogrammer.admin_bio.Utils.Companion.firebaseClearString


class Registration : Fragment() {
    lateinit var root:View
    lateinit var login :ProgressButton
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root =  inflater.inflate(R.layout.registration_fragment, container, false)
        initData()
        return root
    }

    private fun initData() {
        login = root.findViewById<ProgressButton>(R.id.login)
        login.setOnClickListener {
            val roll = root.findViewById<TextInputEditText>(R.id.rollNumber).text.toString()
            val mail = root.findViewById<TextInputEditText>(R.id.mail).text.toString()
            val name = root.findViewById<TextInputEditText>(R.id.name).text.toString()
            if(roll.isEmpty() || mail.isEmpty() || name.isEmpty()) {
                ObjectHolder.MrToast().error(requireActivity(),"Input Required")
                return@setOnClickListener
            }
            login.setLoaderStatus(true)
            saveToDatabase(roll, mail, name)
        }
    }

    private fun saveToDatabase(roll: String?, mail: String?, name: String?) {
        val db = FirebaseDatabase.getInstance().getReference("UserData")
        val clearMail = mail?.firebaseClearString().toString()
        db.child(clearMail).child("name").setValue(name)
        db.child(clearMail).child("mail").setValue(mail)
        db.child(clearMail).child("roll").setValue(roll).addOnCompleteListener {
            ObjectHolder.MrToast().success(requireActivity(),"Saved Successfully")
            root.findViewById<TextInputEditText>(R.id.rollNumber).setText("")
            root.findViewById<TextInputEditText>(R.id.mail).setText("")
            root.findViewById<TextInputEditText>(R.id.name).setText("")
            login.setLoaderStatus(false)
        }

    }
}
