package com.example.imad5112a2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PackingListActivity : AppCompatActivity() {

    private lateinit var btnDisplayList: Button
    private lateinit var btnDisplayQuantity: Button
    private lateinit var btnReturn: Button
    private lateinit var txtResults: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packing_list)

        btnDisplayList = findViewById(R.id.btnDisplayList)
        btnDisplayQuantity = findViewById(R.id.btnDisplayQuantity)
        btnReturn = findViewById(R.id.btnReturn)
        txtResults = findViewById(R.id.txtResults)

        btnDisplayList.setOnClickListener {
            txtResults.text = displayPackingList()
        }

        btnDisplayQuantity.setOnClickListener {
            txtResults.text = displayItemsWithQuantityTwoOrMore()
        }

        btnReturn.setOnClickListener {
            finish()
        }
    }

    /**
     * Displays the full packing list
     */
    private fun displayPackingList(): String {

        if (PackingData.count == 0) {
            return "No packing items have been added."
        }

        var output = ""

        for (i in 0 until PackingData.count) {

            output +=
                "Item: ${PackingData.itemArray[i]}\n" +
                        "Category: ${PackingData.categoryArray[i]}\n" +
                        "Quantity: ${PackingData.quantityArray[i]}\n" +
                        "Comments: ${PackingData.commentsArray[i]}\n\n"
        }

        return output
    }

    /**
     * Displays items with quantity of 2 or more
     */
    private fun displayItemsWithQuantityTwoOrMore(): String {

        var output = ""

        for (i in 0 until PackingData.count) {

            if (PackingData.quantityArray[i] >= 2) {

                output +=
                    "Item: ${PackingData.itemArray[i]}\n" +
                            "Quantity: ${PackingData.quantityArray[i]}\n\n"
            }
        }

        if (output.isEmpty()) {
            output = "No items have a quantity of 2 or more."
        }

        return output
    }
}