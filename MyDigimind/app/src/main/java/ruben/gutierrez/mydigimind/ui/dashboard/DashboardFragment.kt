package ruben.gutierrez.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ruben.gutierrez.mydigimind.R
import ruben.gutierrez.mydigimind.databinding.FragmentDashboardBinding
import ruben.gutierrez.mydigimind.ui.Task
import ruben.gutierrez.mydigimind.ui.home.HomeFragment
import ruben.gutierrez.mydigimind.ui.home.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, {

        })


        val btn_time: Button = root.findViewById(R.id.btn_time)
        btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(cal.time)
            }

            TimePickerDialog(
                root.context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true // is24HourView
            ).show()
        }

        val btn_save = root.findViewById(R.id.btn_time) as Button
        val et_titulo= root.findViewById(R.id.et_answer) as EditText
        val checkMonday = root.findViewById(R.id.cb_monday) as CheckBox
        val checkTuesday = root.findViewById(R.id.cb_tuesday) as CheckBox
        val checkWednesday = root.findViewById(R.id.cb_wednesday) as CheckBox
        val checkThursday = root.findViewById(R.id.cb_thursday) as CheckBox
        val checkFriday = root.findViewById(R.id.cb_friday) as CheckBox
        val checkSaturday = root.findViewById(R.id.cb_saturday) as CheckBox
        val checkSunday = root.findViewById(R.id.cb_sunday) as CheckBox

        btn_save.setOnClickListener {
            var title = et_titulo.text.toString()
            var time = btn_time.text.toString()

            var days = ArrayList<String>()

            if (checkMonday.isChecked)
                days.add("Monday")
            if (checkTuesday.isChecked)
                days.add("Tuesday")
            if (checkWednesday.isChecked)
                days.add("Wednesday")
            if (checkThursday.isChecked)
                days.add("Thursday")
            if (checkFriday.isChecked)
                days.add("Friday")
            if (checkSaturday.isChecked)
                days.add("Saturday")
            if (checkSunday.isChecked)
                days.add("Sunday")

            var task = Task(title, days, time)
            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "New task added", Toast.LENGTH_SHORT).show()
        }
        return root
    }

}