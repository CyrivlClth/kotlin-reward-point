package com.cyrivlclth.rewardpoint.infrastructure.persistence.hibernate

import com.cyrivlclth.rewardpoint.domain.account.model.Account
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import javax.persistence.LockModeType

interface AccountRepositoryHibernate : CrudRepository<Account, Int> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findAndLockById(id: Int): Account?
}