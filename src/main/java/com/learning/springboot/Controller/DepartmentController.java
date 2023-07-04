package com.learning.springboot.Controller;

import com.learning.springboot.entity.Department;
import com.learning.springboot.entity.User;
import com.learning.springboot.error.DepartmentNotFoundException;
import com.learning.springboot.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    private final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department SaveDepartme(@Valid @RequestBody Department department){
        LOG.info("adding Department to db :"+department.toString());
        return departmentService.SaveDepartme(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartment(){
        LOG.info("insie method fetchDepartment");
        return departmentService.fetchDepartmentsList();
    }

    @PostMapping("/registerUser")
    public String registerUser(@RequestBody User user){
        return departmentService.saveUser(user);
    }

    @GetMapping("/department/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        LOG.info("insie method fetchDepartmentById");
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("/department/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        LOG.info("insie method deleteDepartmentById");
        departmentService.deleteDepartmentById(departmentId);
        return "Deleted Depatment :"+departmentId;
    }

    @PutMapping("/department/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long departmentId, @RequestBody Department department){
        LOG.info("insie method updateDepartmentById");
        departmentService.updateDepartmentById(departmentId, department);
        return departmentService.updateDepartmentById(departmentId, department);
    }

    @GetMapping("/department/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name") String departmentName){
        return departmentService.fetchDepartmentByName(departmentName);
    }

}
