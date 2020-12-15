package com.cyrivlclth.rewardpoint.infrastructure.persistence.hibernate

import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import javax.persistence.LockModeType

interface AccountRepositoryHibernate : CrudRepository<AccountPO, Int> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findAndLockById(id: Int): AccountPO?
}