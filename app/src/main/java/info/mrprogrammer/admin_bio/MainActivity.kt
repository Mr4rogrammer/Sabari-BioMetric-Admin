package info.mrprogrammer.admin_bio

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mrprogrammer.attendance.Adapter.ViewPagerAdapter
import com.mrprogrammer.attendance.Fragment.More
import com.mrprogrammer.attendance.Fragment.Registration
import info.mrprogrammer.admin_bio.Fragment.ListOfUser


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



         val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
         val viewPager = findViewById<ViewPager>(R.id.viewPager)

         val fragments: Array<Fragment> = arrayOf<Fragment>(Registration(), ListOfUser(), More())
         val adapter = ViewPagerAdapter(supportFragmentManager, fragments)
         viewPager.adapter = adapter
         tabLayout.setupWithViewPager(viewPager)
    }
}