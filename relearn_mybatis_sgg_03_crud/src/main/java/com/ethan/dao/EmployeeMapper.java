package com.ethan.dao;

import com.ethan.bean.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {


    Employee getEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    void addEmp(Employee employee);
   // Long addEmp(Employee employee);

    void updateEmp(Employee employee);
   // Integer updateEmp(Employee employee);

    void deleteEmpById(Integer id);
    //boolean deleteEmpById(Integer id);


}
