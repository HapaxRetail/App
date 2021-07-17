package hapax.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val backButton = findViewById<Button>(R.id.backButton)

        backButton.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java)

            startActivity(backIntent)
        }
    }
}