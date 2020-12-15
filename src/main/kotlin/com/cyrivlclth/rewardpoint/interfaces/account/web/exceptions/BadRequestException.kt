package com.cyrivlclth.rewardpoint.interfaces.account.web.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class BadRequestException(e: IllegalArgumentException) : IllegalArgumentException(e.message, e.cause)