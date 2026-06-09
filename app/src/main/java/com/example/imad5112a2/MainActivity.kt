package com.example.imad5112a2
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var edtItem: EditText
    private lateinit var edtCategory: EditText
    private lateinit var edtQuantity: EditText
    private lateinit var edtComments: EditText

    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtItem = findViewById(R.id.edtItem)
        edtCategory = findViewById(R.id.edtCategory)
        edtQuantity = findViewById(R.id.edtQuantity)
        edtComments = findViewById(R.id.edtComments)

        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        btnExit = findViewById(R.id.btnExit)

        btnAdd.setOnClickListener {
            addPackingItem()
        }

        btnView.setOnClickListener {
            val intent = Intent(this, PackingListActivity::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }

    /**
     * Adds a packing item to the parallel arrays
     */
    private fun addPackingItem() {

        val item = edtItem.text.toString().trim()
        val category = edtCategory.text.toString().trim()
        val quantityText = edtQuantity.text.toString().trim()
        val comments = edtComments.text.toString().trim()

        // Validation
        if (item.isEmpty() ||
            category.isEmpty() ||
            quantityText.isEmpty() ||
            comments.isEmpty()
        ) {
            Toast.makeText(
                this,
                "Please complete all fields",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val quantity = quantityText.toIntOrNull()

        if (quantity == null || quantity <= 0) {
            Toast.makeText(
                this,
                "Quantity must be greater than 0",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (PackingData.count >= PackingData.MAX_ITEMS) {
            Log.d("PackingApp","User tried adding to a full list")
            Toast.makeText(
                this,
                "Packing list is full",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Store values in parallel arrays
        PackingData.itemArray[PackingData.count] = item
        PackingData.categoryArray[PackingData.count] = category
        PackingData.quantityArray[PackingData.count] = quantity
        PackingData.commentsArray[PackingData.count] = comments

        PackingData.count++
        Log.d("PackingApp", "Successfully added item: $item. Total items: ${PackingData.count}")
        Toast.makeText(
            this,
            "Item Added Successfully",
            Toast.LENGTH_SHORT
        ).show()
        clearFields()
    }

    /**
     * Clears all input fields
     */
    private fun clearFields() {
        edtItem.text.clear()
        edtCategory.text.clear()
        edtQuantity.text.clear()
        edtComments.text.clear()
    }
}
