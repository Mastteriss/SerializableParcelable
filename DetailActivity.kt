package com.example.serializableparcelable

import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    private lateinit var fullNameTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var phoneTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fullNameTextView = findViewById(R.id.fullNameTextView)
        addressTextView = findViewById(R.id.addressTextView)
        phoneTextView = findViewById(R.id.phoneTextView)


        val person: Person? = intent.getParcelableExtra("PERSON")

        person?.let {
            fullNameTextView.text = "${it.firstName} ${it.lastName}"
            addressTextView.text = "Адрес: ${it.address}"
            phoneTextView.text = "Телефон: ${it.phone}"
        }
    }
}