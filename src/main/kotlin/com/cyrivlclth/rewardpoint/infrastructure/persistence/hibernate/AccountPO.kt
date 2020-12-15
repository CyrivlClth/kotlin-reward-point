package com.cyrivlclth.rewardpoint.infrastructure.persistence.hibernate

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "account")
class AccountPO(
    @Id @GeneratedValue val id: Int? = null,
    var amount: Int = 0,
    @Column(updatable = false) @CreationTimestamp val createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp val updatedAt: LocalDateTime = LocalDateTime.now(),
)