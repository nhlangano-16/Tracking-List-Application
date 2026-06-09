package com.example.imad5112a2

object PackingData {

    // Maximum number of packing items
    const val MAX_ITEMS = 20

    // Parallel arrays
    val itemArray = arrayOfNulls<String>(MAX_ITEMS)
    val categoryArray = arrayOfNulls<String>(MAX_ITEMS)
    val commentsArray = arrayOfNulls<String>(MAX_ITEMS)
    val quantityArray = IntArray(MAX_ITEMS)

    // Keeps track of number of items stored
    var count = 0
}
