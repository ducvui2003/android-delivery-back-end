/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:27 PM - 30/10/2024
 *  User: lam-nguyen
 **/

package com.spring.delivery.util.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class Unit(val value: String) {
    @JsonProperty("g")
    GRAM("g"),
    @JsonProperty("ml")
    MILLILITER("ml"),
    @JsonProperty("piece")
    PIECE("piece")
}