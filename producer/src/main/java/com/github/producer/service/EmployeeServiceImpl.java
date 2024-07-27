package com.github.producer.service;

import com.github.producer.config.AsyncConfiguration;
import com.github.producer.repository.EmployeeRepository;
import com.github.producer.repository.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Raman Haurylau
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Async(AsyncConfiguration.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<Page<Employee>> findAll(Pageable pageable) {
        return employeeRepository.findAllBy(pageable);
    }

    @Override
    @Async(AsyncConfiguration.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<Optional<Employee>> findOneById(Long id) {
        return employeeRepository.findOneById(id).thenApply(Optional::ofNullable);
    }

    @Override
    @Async(AsyncConfiguration.TASK_EXECUTOR_SERVICE)
    public CompletableFuture<Optional<Employee>> findOneByName(String name) {
        return employeeRepository.findOneByName(name).thenApply(Optional::ofNullable);
    }
}
