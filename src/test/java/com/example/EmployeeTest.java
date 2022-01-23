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

    @Test
    @DisplayName("Set ID should change from One to Two")
    void setIdShouldChangeFromOneToTwo() {
        employee.setId("2");
        var result = employee.getId();

        assertThat(result).isEqualTo("2");
    }

    @Test
    @DisplayName("Get Salary should return 10000")
    void getSalaryShouldReturn10000() {
        var result = employee.getSalary();

        assertThat(result).isEqualTo(10000);
    }

    @Test
    @DisplayName("Set Salary should change from 10000 to 20000")
    void setSalaryShouldChangeFrom10000To20000() {
        employee.setSalary(20000);
        var result = employee.getSalary();

        assertThat(result).isEqualTo(20000);
    }

    @Test
    @DisplayName("Is Paid should return False")
    void isPaidShouldReturnFalse() {
        var result = employee.isPaid();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Set Paid from False to True")
    void setPaidFromFalseToTrue() {
        employee.setPaid(true);
        var result = employee.isPaid();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Test To String method")
    void testToStringMethod() {
        var result = employee.toString();

        assertThat(result).isEqualTo("Employee [id=1, salary=10000.0]");
    }
}