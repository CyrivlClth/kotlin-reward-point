package com.cyrivlclth.rewardpoint.interfaces.account.web

import com.cyrivlclth.rewardpoint.interfaces.account.facade.dto.AccountDTO
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus

var testAccountId = 1

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class AccountAdminControllerTest @Autowired constructor(
    val restTemplate: TestRestTemplate,
) {
    fun getOrderNo() = System.currentTimeMillis().toString()
    fun getAccountId(): Int {
        testAccountId = testAccountId.inc()
        return testAccountId
    }

    @Test
    fun `Assert find account not exists`() {
        val aid = getAccountId()
        println(">>> id is $aid")
        val result = restTemplate.getForEntity<AccountDTO>("/rest/admin/point-account/$aid")
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(aid, result.body!!.id)
        assertEquals(0, result.body!!.amount)
    }

    @Test
    fun `Assert add amount success`() {
        val aid = getAccountId()
        println(">>> id is $aid")
        var result = restTemplate.postForEntity<AccountDTO>(
            "/rest/admin/point-account/$aid/points",
            AccountAdminController.UpdateRequest(20, getOrderNo()),
        )
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(aid, result.body!!.id)
        assertEquals(20, result.body!!.amount)

        result = restTemplate.postForEntity(
            "/rest/admin/point-account/$aid/points",
            AccountAdminController.UpdateRequest(20, getOrderNo()),
        )
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body!!.id, aid)
        assertEquals(result.body!!.amount, 40)

        result = restTemplate.postForEntity(
            "/rest/admin/point-account/$aid/points",
            AccountAdminController.UpdateRequest(-20, getOrderNo()),
        )
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body!!.id, aid)
        assertEquals(result.body!!.amount, 20)
    }

    @Test
    fun `Assert add zero amount failed`() {
        val aid = getAccountId()
        println(">>> id is $aid")
        var result = restTemplate.postForEntity<AccountDTO>(
            "/rest/admin/point-account/$aid/points",
            AccountAdminController.UpdateRequest(0, getOrderNo()),
        )
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)

        result = restTemplate.getForEntity("/rest/admin/point-account/$aid")
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(aid, result.body!!.id)
        assertEquals(0, result.body!!.amount)
    }

    @Test
    fun `Assert sub amount failed due to out`() {
        val aid = getAccountId()
        println(">>> id is $aid")
        var result = restTemplate.postForEntity<AccountDTO>(
            "/rest/admin/point-account/$aid/points",
            AccountAdminController.UpdateRequest(-20, getOrderNo()),
        )
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)

        result = restTemplate.postForEntity(
            "/rest/admin/point-account/$aid/points",
            AccountAdminController.UpdateRequest(20, getOrderNo()),
        )
        assertEquals(HttpStatus.OK, result.statusCode)
        println(result.body)
        assertEquals(aid, result.body!!.id)
        assertEquals(20, result.body!!.amount)

        result = restTemplate.postForEntity(
            "/rest/admin/point-account/$aid/points",
            AccountAdminController.UpdateRequest(-21, getOrderNo()),
        )
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)

        result = restTemplate.getForEntity("/rest/admin/point-account/$aid")
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(aid, result.body!!.id)
        assertEquals(20, result.body!!.amount)
    }
}