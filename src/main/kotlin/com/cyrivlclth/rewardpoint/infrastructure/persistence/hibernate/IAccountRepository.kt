package com.cyrivlclth.rewardpoint.infrastructure.persistence.hibernate

import com.cyrivlclth.rewardpoint.domain.account.model.Account
import com.cyrivlclth.rewardpoint.domain.account.model.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class IAccountRepository(
    val accountRepositoryHibernate: AccountRepositoryHibernate,
) : AccountRepository {
    override fun findAndLockById(id: Int): Account? {
        return accountRepositoryHibernate.findAndLockById(id)
    }

    override fun findById(id: Int): Account? {
        return accountRepositoryHibernate.findByIdOrNull(id)
    }

    override fun save(account: Account): Account {
        return accountRepositoryHibernate.save(account)
    }

}