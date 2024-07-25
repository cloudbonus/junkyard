package com.github.producer.repository.listener;

import com.github.producer.repository.EmployeeRepository;
import com.github.producer.repository.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Raman Haurylau
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseListener {

    private final EmployeeRepository employeeRepository;

    public List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 1_000; i++) {
            String random = UUID.randomUUID().toString().substring(0, 5);

            Employee employee = new Employee(random);

            employees.add(employee);
        }
        return employees;
    }

    @EventListener
    public void onAppReady(ApplicationReadyEvent event) {
        List<Employee> doctors = generateEmployees();
        employeeRepository.saveAll(doctors);
        log.info("Employee list saved");
    }
}
