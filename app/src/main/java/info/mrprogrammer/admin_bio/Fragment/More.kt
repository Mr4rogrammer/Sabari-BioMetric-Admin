package com.mrprogrammer.attendance.Fragment

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.MaterialDatePicker
import info.mrprogrammer.admin_bio.Attdances
import info.mrprogrammer.admin_bio.Leave
import info.mrprogrammer.admin_bio.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class More : Fragment() {
    lateinit var root:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root =  inflater.inflate(R.layout.more_fragment, container, false)
        initData()
        return root
    }

    private fun initData() {
        val attdance = root.findViewById<CardView>(R.id.attdance)
        val spec = root.findViewById<CardView>(R.id.specfic_addtance)
        val leave = root.findViewById<CardView>(R.id.leave_approvel)

        attdance.setOnClickListener {
            val intent = Intent(requireContext(), Attdances::class.java)
            intent.putExtra("key","0")
            startActivity(intent)
        }

        spec.setOnClickListener {
            val intent = Intent(requireContext(), Attdances::class.java)
            intent.putExtra("key","1")
            startActivity(intent)
        }

        leave.setOnClickListener {
            val intent = Intent(requireContext(), Leave::class.java)
            intent.putExtra("key","0")
            startActivity(intent)
        }
    }

}
