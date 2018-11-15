package com.work.watcharin.createparty

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TimePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.mutation.Precondition
import com.work.watcharin.model.Party
import com.work.watcharin.myapplication.R
import com.work.watcharin.party.PartyActivity
import kotlinx.android.synthetic.main.activity_create_party.*
import java.text.SimpleDateFormat
import java.util.*


class CreatePartyActivity: AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val parties = db.collection("parties")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_party)
        val calendar = Calendar.getInstance()

        create_party_button.setOnClickListener{
            create_party_button.isClickable = false
            val partyName = create_party_name.text.toString()
            val partyLocation = create_party_location.text.toString()
//            val partyTime = create_party_time.text.
            val partyDescription = create_party_description.text.toString()
            val party = Party(topic = partyName, code = partyDescription, time = calendar.time)
                parties.add(party)
                .addOnSuccessListener {
                    create_party_button.isClickable = true
                    val bundle = Bundle().apply { putParcelable("party", party) }
                    val intent = Intent(this, PartyActivity::class.java).putExtra("party", bundle)
                    startActivity(intent)
                }
        }

        create_party_date.text = "Choose a date"
        create_party_time.text = "Choose a time"

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "EEE dd MMM yyyy " // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            create_party_date.text = sdf.format(calendar.time)

        }



        fun updateTime(hour: Int, min: Int) {
            var hours = hour
            val timeSet: String
            when {
                hours > 12 -> {
                    hours -= 12
                    timeSet = "PM"
                }
                hours == 0 -> {
                    hours += 12
                    timeSet = "AM"
                }
                hours == 12 -> timeSet = "PM"
                else -> timeSet = "AM"
            }
            val minutes: String = if (min < 10)
                "0$min"
            else
                min.toString()
            val aTime = StringBuilder().append(hours).append(':').append(minutes).append(" ").append(timeSet).toString()
            create_party_time.text = aTime
        }

        val timePickerListener =
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minutes ->
                updateTime(hourOfDay, minutes)
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minutes)
            }

        create_party_time.setOnClickListener {
            TimePickerDialog(this, timePickerListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show()
        }
        create_party_date.setOnClickListener {
            DatePickerDialog(this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}