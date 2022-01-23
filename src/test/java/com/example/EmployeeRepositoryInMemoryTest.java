package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryInMemoryTest {
    EmployeeRepository employeeRepository = new EmployeeRepositoryInMemory(List.of(
            new Employee("1", 10000),
            new Employee("2", 30000),
            new Employee("3", 40000)
    ));

    @Test
    @DisplayName("Test Find All Method should return Three")
    void testFindAllMethodShouldReturnThree() {
        var result = employeeRepository.findAll().size();

        assertThat(result).isEqualTo(3);
    }

}