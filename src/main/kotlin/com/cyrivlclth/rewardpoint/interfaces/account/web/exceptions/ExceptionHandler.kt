package com.cyrivlclth.rewardpoint.interfaces.account.web.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseBody
    fun argumentExceptionHandler(
        request: HttpServletRequest,
        e: IllegalArgumentException,
        response: HttpServletResponse,
    ): BadRequestException {
        response.status = HttpStatus.BAD_REQUEST.value()
        return BadRequestException(e)
    }
}