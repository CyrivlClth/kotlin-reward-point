package com.cyrivlclth.rewardpoint.domain.account.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@DynamicUpdate
class Account(
    @Id @GeneratedValue val id: Int? = null,
    @Column(updatable = false) @CreationTimestamp val createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    var amount: Int = 0
        set(value) {
            require(value >= 0)
            { IllegalArgumentException("amount is less than zero") }
            field = value
        }

    fun addPoint(pointVO: PointVO) {
        amount += pointVO.amount
    }
}