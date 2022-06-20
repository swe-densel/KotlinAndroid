package ph.gcash.cadet.denselross.serrano.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import ph.gcash.cadet.denselross.serrano.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnSubmit.setOnClickListener {
//            Toast.makeText(
//                applicationContext,
//                "Something",
//                Toast.LENGTH_SHORT
//            ).show()
//        }

        binding.btnSubmit.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        binding.btnClose.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var message = ""

        v?.let {
            when(it.id){
                R.id.btn_cancel -> message = "CANCEL"
                R.id.btn_submit -> message = "SUBMIT"
                R.id.btn_close -> message = "CLOSE"
            }
        }

        val username = binding.etUsername.text
        val username2 = binding.tiUsername.text
        val spinner = binding.spItems.selectedItem

        Snackbar.make(
            binding.root,
            "$message $username $username2 $spinner",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}