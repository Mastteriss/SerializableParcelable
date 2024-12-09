package com.example.serializableparcelable

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var personListView: ListView
    private val personList = mutableListOf<Person>()
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        addressEditText = findViewById(R.id.addressEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        saveButton = findViewById(R.id.saveButton)
        personListView = findViewById(R.id.personListView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        personListView.adapter = adapter

        saveButton.setOnClickListener {
            savePerson()
        }
        personListView.setOnItemClickListener { _, _, position, _ ->
            val person = personList[position]
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("PERSON", person)
            }
            startActivity(intent)
        }
    }

    private fun savePerson() {
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val address = addressEditText.text.toString()
        val phone = phoneEditText.text.toString()

        if (firstName.isNotBlank() && lastName.isNotBlank()) {
            val person = Person(firstName, lastName, address, phone)
            personList.add(person)
            adapter.add("$firstName $lastName")
            adapter.notifyDataSetChanged()


            firstNameEditText.text.clear()
            lastNameEditText.text.clear()
            addressEditText.text.clear()
            phoneEditText.text.clear()
        } else {
            Toast.makeText(this, "Пожалуйста, введите имя и фамилию", Toast.LENGTH_SHORT).show()
        }
    }
}