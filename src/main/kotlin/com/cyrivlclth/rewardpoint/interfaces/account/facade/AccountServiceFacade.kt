package com.cyrivlclth.rewardpoint.interfaces.account.facade

import com.cyrivlclth.rewardpoint.application.AccountService
import com.cyrivlclth.rewardpoint.domain.account.model.Account
import com.cyrivlclth.rewardpoint.domain.account.model.PointVO
import com.cyrivlclth.rewardpoint.interfaces.account.facade.dto.AccountDTO
import org.springframework.stereotype.Service

@Service
class AccountServiceFacade(
    private val accountService: AccountService
) {
    fun findById(id: Int): AccountDTO {
        return accountService.findById(id).toDTO()
    }

    fun addPoint(id: Int, pointVO: PointVO): AccountDTO {
        return accountService.addPoint(id, pointVO).toDTO()
    }

    fun Account.toDTO() = AccountDTO(
        id ?: 0, amount,
    )
}