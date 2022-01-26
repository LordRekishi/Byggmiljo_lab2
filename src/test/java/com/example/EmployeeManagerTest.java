package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EmployeeManagerTest {
    EmployeeRepository employeeRepository = new EmployeeRepositoryStub(List.of(
            new Employee("1", 10000),
            new Employee("2", 5000)));
    BankService bankService = mock(BankService.class);
    EmployeeManager employeeManager = new EmployeeManager(employeeRepository, bankService);

    @Test
    @DisplayName("Pay Employees should return 2")
    void payEmployeesShouldReturn2() {
        var result = employeeManager.payEmployees();

        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("Pay Employees should return runtime Exception")
    void payEmployeesShouldReturnRuntimeException() {
        doThrow(new RuntimeException()).when(bankService).pay(anyString(), anyDouble());

        employeeManager.payEmployees();

        verify(bankService, times(2)).pay(anyString(), anyDouble());
        assertThat(employeeRepository.findAll().get(0).isPaid()).isFalse();
    }
}