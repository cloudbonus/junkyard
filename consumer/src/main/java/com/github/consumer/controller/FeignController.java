package com.github.consumer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author Raman Haurylau
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("feign")
public class FeignController {

    private final EmployeeClient employeeClient;

    @GetMapping
    public CompletableFuture<ResponseEntity<String>> findEmployeeById(@RequestParam Long value) {
        CompletableFuture<ResponseEntity<?>> cp1 = CompletableFuture.supplyAsync(() ->
                employeeClient.findEmployeeById(value));

        CompletableFuture<ResponseEntity<?>> cp2 = CompletableFuture.supplyAsync(() ->
                employeeClient.findEmployeeById(value + 1));

        return CompletableFuture.allOf(cp1, cp2)
                .thenApply(v -> {
                    String body1 = Objects.requireNonNull(cp1.join().getBody()).toString();
                    String body2 = Objects.requireNonNull(cp2.join().getBody()).toString();
                    String combinedResult = body1 + ";" + body2;
                    return ResponseEntity.ok(combinedResult);
                });
    }
}
