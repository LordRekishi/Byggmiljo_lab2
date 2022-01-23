package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {
    Employee employee = new Employee("1", 10000);

    @Test
    @DisplayName("Get ID should return One")
    void getIdShouldReturnOne() {
        var result = employee.getId();

        assertThat(result).isEqualTo("1");
    }

}