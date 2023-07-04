package com.learning.springboot.service;

import com.learning.springboot.entity.Department;
import com.learning.springboot.entity.User;
import com.learning.springboot.error.DepartmentNotFoundException;
import com.learning.springboot.repository.DepartmentRepository;
import com.learning.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public Department SaveDepartme(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentsList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent())
            throw new DepartmentNotFoundException("Department with give id is not found");
        return departmentRepository.findById(departmentId).get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
         departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) {


        Department deptDB = departmentRepository.findById(departmentId).get();

        if(Objects.nonNull(department.getDepartmentAddress()) && !"".equals(department.getDepartmentAddress()))
            deptDB.setDepartmentAddress(department.getDepartmentAddress());

        if(Objects.nonNull(department.getDepartmentName()) && !"".equals(department.getDepartmentName()))
            deptDB.setDepartmentName(department.getDepartmentName());

        if(Objects.nonNull(department.getDepartmentCode()) && !"".equals(department.getDepartmentCode()))
            deptDB.setDepartmentCode(department.getDepartmentCode());

        departmentRepository.save(deptDB);
        return deptDB
                ;
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findBydepartmentNameIgnoreCase(departmentName);
    }

    @Override
    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "success";
    }
}
