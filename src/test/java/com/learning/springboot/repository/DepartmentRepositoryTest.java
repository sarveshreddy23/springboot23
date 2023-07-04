package com.learning.springboot.repository;

import com.learning.springboot.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Mech")
                .departmentAddress("Banglore")
                .departmentCode("MC-09")
                .build();
        entityManager.persist(department);
    }

    @Test
    public void findById(){
        Department department = departmentRepository.findById(1L).get();

        assertEquals(department.getDepartmentName(), "Mech");
    }

}