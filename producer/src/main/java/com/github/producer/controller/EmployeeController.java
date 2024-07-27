package com.github.producer.controller;

import com.github.producer.config.AsyncConfiguration;
import com.github.producer.repository.entity.Employee;
import com.github.producer.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author Raman Haurylau
 */
@Slf4j
@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @Async(AsyncConfiguration.TASK_EXECUTOR_CONTROLLER)
    public CompletableFuture<ResponseEntity<?>> findAll(Pageable paging) {
        return employeeService
                .findAll(paging)
                .<ResponseEntity<?>>thenApply(ResponseEntity::ok)
                .exceptionally(handleFindEmployeesFailure);
    }

    @GetMapping("id")
    @SneakyThrows
    @Async(AsyncConfiguration.TASK_EXECUTOR_CONTROLLER)
    public CompletableFuture<ResponseEntity<?>> findEmployeeById(@RequestParam Long value) {
        Thread.sleep(3000);
        return employeeService
                .findOneById(value)
                .thenApply(mapMaybeEmployeeToResponse)
                .exceptionally(handleFindEmployeeByIdFailure.apply(value));
    }

    @GetMapping("name")
    @Async(AsyncConfiguration.TASK_EXECUTOR_CONTROLLER)
    public CompletableFuture<ResponseEntity<?>> findEmployeeByName(@RequestParam String value) {
        return employeeService
                .findOneByName(value)
                .<ResponseEntity<?>>thenApply(maybeEmployee -> maybeEmployee.map(e -> {
                            Link link = WebMvcLinkBuilder.linkTo(EmployeeController.class).withSelfRel();
                            return ResponseEntity.ok(EntityModel.of(e).add(link.withRel("all-employees")));
                        })
                        .orElse(ResponseEntity.notFound().build()))
                .exceptionally(ex -> ResponseEntity.internalServerError().build());
    }

    private static final Function<Throwable, ResponseEntity<?>> handleFindEmployeesFailure = throwable -> {
        log.error("Unable to retrieve users", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    private static final Function<Optional<Employee>, ResponseEntity<?>> mapMaybeEmployeeToResponse = maybeEmployee -> maybeEmployee
            .<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());

    private static final Function<Long, Function<Throwable, ResponseEntity<?>>> handleFindEmployeeByIdFailure = employeeId -> throwable -> {
        log.error(String.format("Unable to retrieve employee for id: %s", employeeId), throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
