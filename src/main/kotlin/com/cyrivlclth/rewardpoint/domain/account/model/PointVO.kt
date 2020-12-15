package com.cyrivlclth.rewardpoint.domain.account.model

data class PointVO(
    val amount: Int,
    val identity: String,
) {
    init {
        require(identity.isNotBlank()) { IllegalArgumentException("identify required") }
        require(identity.length <= 32) { IllegalArgumentException("identify is too long") }
        require(amount != 0) { IllegalArgumentException("change amount cannot be zero") }
    }
}