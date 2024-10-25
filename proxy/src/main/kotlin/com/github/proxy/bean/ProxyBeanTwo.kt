package com.github.proxy.bean

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * @author Raman Haurylau
 */
@Service
class ProxyBeanTwo(val proxyBeanOne: ProxyBeanOne) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun invokeMethodWithoutTransaction() {
        proxyBeanOne.invokeMethodWithoutTransaction()
        log.info("{}", proxyBeanOne.javaClass)
        log.info("{}", proxyBeanOne.counter)
    }

    fun invokeMethodWithTransaction() {
        proxyBeanOne.invokeMethodWithTransactionButStandalone()
        log.info("{}", proxyBeanOne.javaClass)
        log.info("{}", proxyBeanOne.counter)
    }
}