package com.cyrivlclth.rewardpoint.infrastructure.persistence.hibernate

import com.cyrivlclth.rewardpoint.domain.account.model.Account
import com.cyrivlclth.rewardpoint.domain.account.model.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import kotlin.math.absoluteValue

@Repository
class IAccountRepository(
    val accountRepositoryHibernate: AccountRepositoryHibernate,
    val accountLogRepositoryHibernate: AccountLogRepositoryHibernate,
) : AccountRepository {
    override fun findAndLockById(id: Int): Account? {
        return accountRepositoryHibernate.findAndLockById(id)?.toEntity()
    }

    override fun findById(id: Int): Account? {
        return accountRepositoryHibernate.findByIdOrNull(id)?.toEntity()
    }

    @Transactional
    override fun save(account: Account): Account {
        val po = accountRepositoryHibernate.save(account.toPO())
        accountLogRepositoryHibernate.saveAll(account.points.map {
            AccountLogPO(
                orderNo = it.identity,
                account = po,
                type = if (it.amount > 0) 1 else 2,
                changeAmount = it.amount.absoluteValue,
                status = 1,
            )
        })
        return po.toEntity()
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