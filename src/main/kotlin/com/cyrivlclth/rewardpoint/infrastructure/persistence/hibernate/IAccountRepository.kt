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
        return accountRepositoryHibernate.findAndLockById(id)?.toEntity()
    }

    override fun findById(id: Int): Account? {
        return accountRepositoryHibernate.findByIdOrNull(id)?.toEntity()
    }

    override fun save(account: Account): Account {
        return accountRepositoryHibernate.save(account.toPO()).toEntity()
    }

    fun Account.toPO() = AccountPO(
        id = id,
        amount = amount,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

    fun AccountPO.toEntity() = Account(
        id = id,
        amount = amount,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

}