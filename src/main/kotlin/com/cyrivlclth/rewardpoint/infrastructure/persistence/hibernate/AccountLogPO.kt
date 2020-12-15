package com.cyrivlclth.rewardpoint.infrastructure.persistence.hibernate

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "account_log")
class AccountLogPO(
    @Id @GeneratedValue val id: Int? = null,
    @Column(unique = true, length = 32) val orderNo: String,
    @ManyToOne @JoinColumn(foreignKey = ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
    val account: AccountPO,
    @Column(length = 4) val type: Int,
    val changeAmount: Int,
    val status: Int,
    val amountAfter: Int = account.amount,
    @Column(updatable = false) @CreationTimestamp val createdAt: LocalDateTime = LocalDateTime.now(),
)