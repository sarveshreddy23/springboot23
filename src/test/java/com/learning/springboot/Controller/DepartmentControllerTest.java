package com.learning.springboot.Controller;

import com.learning.springboot.entity.Department;
import com.learning.springboot.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    DepartmentService departmentService;

    Department department;
    @BeforeEach
    void setUp() {
         department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Banglore")
                .departmentCode("IT-01")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {

        Department inputDepartment =Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Banglore")
                .departmentCode("IT-01")
                .build();

        Mockito.when(departmentService.SaveDepartme(inputDepartment)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"departmentName\":\"IT\",\n" +
                        "    \"departmentAddress\":\"Banglore\",\n" +
                        "    \"departmentCode\":\"IT-01\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    void fetchDepartment() throws Exception {

        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/department/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));

    }
}