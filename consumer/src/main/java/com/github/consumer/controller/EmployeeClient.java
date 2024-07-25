package com.github.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Raman Haurylau
 */
@FeignClient(name = "employees-client", url = "http://localhost:8080/employees")
public interface EmployeeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/id", produces = "application/json")
    ResponseEntity<?> findEmployeeById(@RequestParam Long value);

}
