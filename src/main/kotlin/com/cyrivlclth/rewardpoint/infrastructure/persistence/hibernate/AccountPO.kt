package com.cyrivlclth.rewardpoint.infrastructure.persistence.hibernate

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "account")
@DynamicUpdate
class AccountPO(
    @Id val id: Int,
    var amount: Int = 0,
    @Column(updatable = false) @CreationTimestamp val createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp val updatedAt: LocalDateTime = LocalDateTime.now(),
)