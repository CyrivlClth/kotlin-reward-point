package com.cyrivlclth.rewardpoint.domain.account.model

import java.time.LocalDateTime


class Account(
    val id: Int,
    amount: Int = 0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    var amount = amount
        set(value) {
            require(value >= 0)
            { IllegalArgumentException("amount is less than zero") }
            field = value
        }

    fun addPoint(pointVO: PointVO) {
        amount += pointVO.amount
    }
}