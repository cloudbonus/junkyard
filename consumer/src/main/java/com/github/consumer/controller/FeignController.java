package com.github.consumer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * @author Raman Haurylau
 */
@Tag(name = "Feign", description = "the Feign usage")
@RestController
@RequiredArgsConstructor
@RequestMapping("feign")
public class FeignController {

    private final EmployeeClient employeeClient;

    @GetMapping
    @Operation(
            summary = "Fetch CompletableFuture object",
            description = "fetches one CompletableFuture object from external api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public CompletableFuture<Map<String, String>> findEmployeeById(@RequestParam Long value) {
        CompletableFuture<ResponseEntity<?>> cp1 = CompletableFuture.supplyAsync(() ->
                employeeClient.findEmployeeById(value));

        CompletableFuture<ResponseEntity<?>> cp2 = CompletableFuture.supplyAsync(() ->
                employeeClient.findEmployeeById(value + 1));

        return CompletableFuture.allOf(cp1, cp2)
                .thenApply(v -> {
                    String body1 = Objects.requireNonNull(cp1.join().getBody()).toString();
                    String body2 = Objects.requireNonNull(cp2.join().getBody()).toString();
                    return Map.of("object", body1 + " " + body2);
                });
    }
}
