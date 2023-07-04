package com.learning.springboot.service;

import com.learning.springboot.entity.Department;
import com.learning.springboot.entity.User;
import com.learning.springboot.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    Department SaveDepartme(Department department);

    List<Department> fetchDepartmentsList();

    Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartmentById(Long departmentId, Department department);

    Department fetchDepartmentByName(String departmentName);

    String saveUser(User user);
}
