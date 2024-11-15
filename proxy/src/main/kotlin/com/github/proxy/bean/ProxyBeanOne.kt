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
class ProxyBeanOne(private val userRepository: UserRepository) : ProxyBean {
    override var counter: Int = 0

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun invokeMethodWithoutTransaction() {
        val person = Person()
        invokeMethodWithTransaction(person)
    }

    @Transactional
    override fun invokeMethodWithTransaction(person: Person) {
        userRepository.save(person)
        counter++
        log.info("{}", counter)
    }

    @Transactional
    override fun invokeMethodWithTransactionButStandalone() {
        val person = Person()
        userRepository.save(person)
        log.info("{}", this.javaClass)
        counter++
        log.info("{}", counter)
    }
}