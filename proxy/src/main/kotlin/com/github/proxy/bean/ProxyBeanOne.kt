package com.github.proxy.bean

import com.github.proxy.entity.Person
import com.github.proxy.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Raman Haurylau
 */
@Service
class ProxyBeanOne(var counter: Int = 0, private val userRepository: UserRepository) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun invokeMethodWithoutTransaction() {
        val person = Person()
        invokeMethodWithTransaction(person)
    }

    @Transactional
    fun invokeMethodWithTransaction(person: Person) {
        userRepository.save(person)
        log.info("{}", counter++)
    }

    @Transactional
    fun invokeMethodWithTransactionButStandalone(): Int {
        val person = Person()
        userRepository.save(person)
        log.info("{}", this.javaClass)
        log.info("{}", counter++)
        return counter
    }
}