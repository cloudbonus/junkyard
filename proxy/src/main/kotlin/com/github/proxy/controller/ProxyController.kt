package com.github.proxy.controller

import com.github.proxy.bean.ProxyBeanTwo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Raman Haurylau
 */
@RestController
class ProxyController(val proxyBeanTwo: ProxyBeanTwo) {

    @GetMapping
    fun sendSignal() = proxyBeanTwo.invokeMethodWithoutTransaction()
}