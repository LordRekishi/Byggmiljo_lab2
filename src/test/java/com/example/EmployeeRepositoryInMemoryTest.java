package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeRepositoryInMemoryTest {
    List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("1", 10000),
            new Employee("2", 30000),
            new Employee("3", 40000)));
    EmployeeRepository employeeRepository = new EmployeeRepositoryInMemory(employees);

    @Test
    @DisplayName("Test Find All with empty List should return Zero")
    void testFindAllWithEmptyListShouldReturnZero() {
        EmployeeRepository employeeRepository = new EmployeeRepositoryInMemory(List.of());

        var result = employeeRepository.findAll().size();

        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Test Find All Method should return Three")
    void testFindAllMethodShouldReturnThree() {
        var result = employeeRepository.findAll().size();

        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Test Save Method with new employee should return Four")
    void testSaveMethodWithNewEmployeeShouldReturnFour() {
        employeeRepository.save(new Employee("4", 2000));
        var result = employeeRepository.findAll().size();

        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Add Employee with same ID Should update to new Employee")
    void addEmployeeWithSameIdShouldReturnUpdatedSalary() {
        var oldEmployee = employeeRepository.findAll().get(0);

        employeeRepository.save(new Employee("1", 3000));

        Employee newEmployee = employeeRepository.findAll().get(0);
        var list = employeeRepository.findAll();

        assertThat(newEmployee).isNotEqualTo(oldEmployee);
        assertThat(list).contains(newEmployee).doesNotContain(oldEmployee);
    }
}