package com.learning.springboot.service;

import com.learning.springboot.entity.Department;
import com.learning.springboot.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    DepartmentService departmentService;

    @MockBean
    DepartmentRepository departmentRepository;
    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Chennai")
                .departmentCode("IT-05")
                .build();

        Mockito.when(departmentRepository.findBydepartmentNameIgnoreCase("IT")).thenReturn(department);
    }

    @Test
    @DisplayName("validate Department Name")
    public void whenValidDepatmentName_thenDepartmentShouldBeFound(){
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());

    }
}