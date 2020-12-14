package com.cyrivlclth.rewardpoint.domain.account.model

interface AccountRepository {
    fun findAndLockById(id: Int): Account?
    fun findById(id: Int): Account?
    fun save(account: Account): Account
}