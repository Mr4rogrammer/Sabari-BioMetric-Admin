package info.mrprogrammer.admin_bio

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.messaging.FirebaseMessaging
import com.mrprogrammer.attendance.Adapter.ViewPagerAdapter
import com.mrprogrammer.attendance.Fragment.More
import com.mrprogrammer.attendance.Fragment.Registration
import info.mrprogrammer.admin_bio.Fragment.ListOfUser


class MainActivity : AppCompatActivity() {
    val PERMISSION_ID = 7077
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission()
         val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
         val viewPager = findViewById<ViewPager>(R.id.viewPager)

         val fragments: Array<Fragment> = arrayOf<Fragment>(Registration(), ListOfUser(), More())
         val adapter = ViewPagerAdapter(supportFragmentManager, fragments)
         viewPager.adapter = adapter
         tabLayout.setupWithViewPager(viewPager)
    }

    private fun requestPermission(){
        if(ContextCompat.checkSelfPermission(applicationContext,android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), PERMISSION_ID)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            FirebaseMessaging.getInstance().subscribeToTopic("admin")
        }
    }
}

