package hapax.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hapax.app.databinding.AppLayoutBinding
import java.util.*

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = AppLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

