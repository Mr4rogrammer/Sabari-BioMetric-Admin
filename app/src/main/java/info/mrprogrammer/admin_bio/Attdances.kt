package info.mrprogrammer.admin_bio

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mrprogrammer.Utils.Widgets.ProgressButton
import com.mrprogrammer.mrshop.ObjectHolder.ObjectHolder
import info.mrprogrammer.admin_bio.Model.AttdanceModel
import info.mrprogrammer.admin_bio.Utils.Companion.getCurrentTime
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Attdances : AppCompatActivity() {
    var stringValue:String? =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attdances)
        stringValue = intent.getStringExtra("key")
        if(stringValue == null) {
            finish()
            return
        }
        when(stringValue) {
            "0" -> {
                val attdance = findViewById<LinearLayout>(R.id.attdance)
                attdance.visibility = View.VISIBLE
                initAttdances()
            }

            "1" -> {
                val attdance = findViewById<LinearLayout>(R.id.sp_attdance)
                attdance.visibility = View.VISIBLE
                initSpAttdances()
            }
        }
    }


    private fun getSpData(function: (data:AttdanceModel) -> Unit) {
        val db = FirebaseDatabase.getInstance().getReference("attdances").child("spdata")
        db.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(AttdanceModel::class.java)?.let { function.invoke(it) }
            }

            override fun onCancelled(error: DatabaseError) {
                ObjectHolder.MrToast().error(this@Attdances,error.message)
            }

        })
    }

    private fun initSpAttdances() {
        val timeStart = findViewById<TextView>(R.id.sp_start_time)
        val timeEnd = findViewById<TextView>(R.id.sp_end_time)
        val radius = findViewById<TextView>(R.id.sp_radius)
        val remark = findViewById<TextView>(R.id.sp_remark)
        val button = findViewById<ProgressButton>(R.id.sp_button)
        val username = findViewById<TextView>(R.id.sp_username)
        val password = findViewById<TextView>(R.id.sp_password)

        getSpData {
            timeStart.text = it.timeStart
            timeEnd.text = it.timeEnd
            radius.text = it.radius
            remark.text = it.remark

            username.text = it.username
            password.text = it.password

        }

        timeEnd.setOnClickListener {
            showTimePickerDialog(timeEnd)
        }

        timeStart.setOnClickListener {
            showTimePickerDialog(timeStart)
        }

        button.setOnClickListener {
            if(radius.text.isEmpty() || remark.text.isEmpty() || username.text.isEmpty() || password.text.isEmpty()) {
                ObjectHolder.MrToast().error(this,"Please enter data")
                return@setOnClickListener
            }

            saveToSpDatabase(radius.text.toString(), remark.text.toString(), timeEnd.text.toString(), timeStart.text.toString(), username.text.toString(), password.text.toString())
        }

    }


    private fun saveToSpDatabase(radius: String, remark: String, timeEnd: String, timeStart: String, userName:String, password:String) {
        val db = FirebaseDatabase.getInstance().getReference("attdances").child("spdata")
        db.child("radius").setValue(radius)
        db.child("remark").setValue(remark)
        db.child("timeStart").setValue(timeStart)
        db.child("username").setValue(userName)
        db.child("password").setValue(password)
        db.child("timeEnd").setValue(timeEnd).addOnCompleteListener {
            ObjectHolder.MrToast().success(this,"Update Completed")
        }
    }



















    private fun getData(function: (data:AttdanceModel) -> Unit) {
        val db = FirebaseDatabase.getInstance().getReference("attdances").child("data")
        db.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(AttdanceModel::class.java)?.let { function.invoke(it) }
            }

            override fun onCancelled(error: DatabaseError) {
                ObjectHolder.MrToast().error(this@Attdances,error.message)
            }

        })
    }

    private fun initAttdances() {
        val timeStart = findViewById<TextView>(R.id.start_time)
        val timeEnd = findViewById<TextView>(R.id.end_time)
        val radius = findViewById<TextView>(R.id.radius)
        val remark = findViewById<TextView>(R.id.remark)
        val button = findViewById<ProgressButton>(R.id.button)

        getData {
            timeStart.text = it.timeStart
            timeEnd.text = it.timeEnd
            radius.text = it.radius
            remark.text = it.remark
        }


        timeEnd.setOnClickListener {
            showTimePickerDialog(timeEnd)
        }

        timeStart.setOnClickListener {
            showTimePickerDialog(timeStart)
        }

        button.setOnClickListener {
            if(radius.text.isEmpty() || remark.text.isEmpty()) {
                ObjectHolder.MrToast().error(this,"Please enter data")
                return@setOnClickListener
            }

            saveToDatabase(radius.text.toString(), remark.text.toString(), timeEnd.text.toString(), timeStart.text.toString())
        }

    }

    private fun saveToDatabase(radius: String, remark: String, timeEnd: String, timeStart: String) {
        val db = FirebaseDatabase.getInstance().getReference("attdances").child("data")
        db.child("radius").setValue(radius)
        db.child("remark").setValue(remark)
        db.child("timeStart").setValue(timeStart)
        db.child("timeEnd").setValue(timeEnd).addOnCompleteListener {
            ObjectHolder.MrToast().success(this,"Update Completed")
        }
    }

    private fun formatTime(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
    fun showTimePickerDialog(textView: TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                val timeText = formatTime(selectedHour, selectedMinute)

                textView.text = timeText
            },
            hour,
            minute,
            false
        )
        timePickerDialog.show()
    }
}