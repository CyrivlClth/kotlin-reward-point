package com.cyrivlclth.rewardpoint.domain.account.model

data class PointVO(
    val amount: Int,
) {
    init {
        require(amount != 0) { IllegalArgumentException("change amount cannot be zero") }
    }
}