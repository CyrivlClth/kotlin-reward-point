package com.cyrivlclth.rewardpoint.interfaces.account.web

import com.cyrivlclth.rewardpoint.domain.account.model.PointVO
import com.cyrivlclth.rewardpoint.interfaces.account.facade.AccountServiceFacade
import com.cyrivlclth.rewardpoint.interfaces.account.facade.dto.AccountDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AccountAdminController(
    private val accountServiceFacade: AccountServiceFacade
) {
    @GetMapping("/point-account/{id}")
    fun getPointAccount(@PathVariable id: Int): AccountDTO {
        return accountServiceFacade.findById(id)
    }

    @PutMapping("/point-account/{id}")
    fun updatePointAccount(@PathVariable id: Int, @RequestBody updateRequest: UpdateRequest): AccountDTO {
        return accountServiceFacade.addPoint(id, updateRequest.toPointVO())
    }

    fun UpdateRequest.toPointVO() = PointVO(amount)

    data class UpdateRequest(val amount: Int)
}