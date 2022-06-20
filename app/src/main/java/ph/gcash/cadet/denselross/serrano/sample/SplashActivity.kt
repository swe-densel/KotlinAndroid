package ph.gcash.cadet.denselross.serrano.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ph.gcash.cadet.denselross.serrano.sample.databinding.ActivityMainBinding
import ph.gcash.cadet.denselross.serrano.sample.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProceed.setOnClickListener {
            val goToPokemonActivity = Intent(this, PokemonActivity::class.java)
            goToPokemonActivity.putExtra("username", "extra data")

            val bundle = Bundle()
            bundle.putString("bundle_string", "Bundle String")
            goToPokemonActivity.putExtras(bundle)

            startActivity(goToPokemonActivity)
            finish()
        }
    }
}