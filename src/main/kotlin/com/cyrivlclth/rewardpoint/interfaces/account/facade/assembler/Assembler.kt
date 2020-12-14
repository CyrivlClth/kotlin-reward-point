package com.cyrivlclth.rewardpoint.interfaces.account.facade.assembler

import com.cyrivlclth.rewardpoint.domain.account.model.Account
import com.cyrivlclth.rewardpoint.interfaces.account.facade.dto.AccountDTO

fun Account.toDTO() = AccountDTO(
    id ?: 0, amount,
)