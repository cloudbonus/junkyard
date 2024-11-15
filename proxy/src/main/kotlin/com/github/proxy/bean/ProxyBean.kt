package com.github.proxy.bean

import com.github.proxy.entity.Person

/**
 * @author Raman Haurylau
 */
interface ProxyBean {
    var counter: Int
    fun invokeMethodWithoutTransaction()
    fun invokeMethodWithTransaction(person: Person)
    fun invokeMethodWithTransactionButStandalone()
}