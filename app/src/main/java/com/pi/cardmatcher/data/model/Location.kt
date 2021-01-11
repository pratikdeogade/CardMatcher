package com.pi.cardmatcher.data.model

data class Location (

    val street : Street,
    val city : String,
    val state : String,
    val country : String,
    val postcode : String,
    val coordinates : Coordinates,
    val timezone : Timezone
)