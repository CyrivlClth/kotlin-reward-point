package com.cyrivlclth.rewardpoint.application

import com.cyrivlclth.rewardpoint.domain.account.model.Account
import com.cyrivlclth.rewardpoint.domain.account.model.AccountRepository
import com.cyrivlclth.rewardpoint.domain.account.model.PointVO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {
    fun findById(id: Int): Account {
        return accountRepository.findById(id) ?: Account(id = id)
    }

    @Transactional
    fun addPoint(id: Int, pointVO: PointVO): Account {
        val account = accountRepository.findAndLockById(id) ?: Account(id = id)
        account.addPoint(pointVO)
        return accountRepository.save(account)
    }
}