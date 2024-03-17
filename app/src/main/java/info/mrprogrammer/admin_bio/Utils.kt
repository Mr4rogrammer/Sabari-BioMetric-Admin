package info.mrprogrammer.admin_bio

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mrprogrammer.mrshop.ObjectHolder.ObjectHolder
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    companion object {
        fun String.firebaseClearString(): String? {
            var aString = this
            aString = aString.replace("@", "").toString()
            aString = aString.replace(".", "").toString()
            aString = aString.replace("#", "").toString()
            aString = aString.replace("$", "").toString()
            aString = aString.replace("[", "").toString()
            aString = aString.replace("]", "").toString()
            return aString
        }

        fun getAddress(context: Context, location:Location): Address? {
            val addresses: List<Address>?
            val geocoder: Geocoder = Geocoder(context, Locale.getDefault())
            addresses = geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                1
            )
            addresses?.get(0)?.let { ObjectHolder.setAddress(address = it) }
           return addresses?.get(0)
        }

        fun showDialog(context: Context,message:String) {
            MaterialAlertDialogBuilder(context)
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        fun getCurrentDate(): String {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return dateFormat.format(calendar.time)
        }

         fun getCurrentTime(): String {
            val calendar = Calendar.getInstance()
            val timeFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
            return timeFormat.format(calendar.time)
        }


    }

}