package com.github.producer.service;

import com.github.producer.repository.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Raman Haurylau
 */
public interface EmployeeService {
    CompletableFuture<Page<Employee>> findAll(Pageable pageable);
    CompletableFuture<Optional<Employee>> findOneById(Long id);
    CompletableFuture<Optional<Employee>> findOneByName(String name);
}
