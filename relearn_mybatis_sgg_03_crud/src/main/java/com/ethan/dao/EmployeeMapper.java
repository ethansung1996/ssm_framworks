package com.ethan.dao;

import com.ethan.bean.Employee;

public interface EmployeeMapper {


    Employee getEmpById(Integer id);

    void addEmp(Employee employee);
   // Long addEmp(Employee employee);

    void updateEmp(Employee employee);
   // Integer updateEmp(Employee employee);

    void deleteEmpById(Integer id);
    //boolean deleteEmpById(Integer id);


}
