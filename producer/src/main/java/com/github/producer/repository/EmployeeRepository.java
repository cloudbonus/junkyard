package com.github.producer.repository;

import com.github.producer.config.AsyncConfiguration;
import com.github.producer.repository.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

/**
 * @author Raman Haurylau
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Async(AsyncConfiguration.TASK_EXECUTOR_REPOSITORY)
    CompletableFuture<Page<Employee>> findAllBy(Pageable pageable);

    @Async(AsyncConfiguration.TASK_EXECUTOR_REPOSITORY)
    CompletableFuture<Employee> findOneById(Long id);

    @Async(AsyncConfiguration.TASK_EXECUTOR_REPOSITORY)
    CompletableFuture<Employee> findOneByName(String name);
}
