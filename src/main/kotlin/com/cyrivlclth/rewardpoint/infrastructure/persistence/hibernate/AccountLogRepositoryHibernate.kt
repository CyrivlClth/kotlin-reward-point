package com.cyrivlclth.rewardpoint.infrastructure.persistence.hibernate

import org.springframework.data.repository.CrudRepository

interface AccountLogRepositoryHibernate : CrudRepository<AccountLogPO, Int>