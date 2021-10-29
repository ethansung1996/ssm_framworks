package com.ethan.dao;

import com.ethan.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {


    Employee getEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    void addEmp(Employee employee);
   // Long addEmp(Employee employee);

    void updateEmp(Employee employee);
   // Integer updateEmp(Employee employee);

    void deleteEmpById(Integer id);
    //boolean deleteEmpById(Integer id);

    //封装多条数据到list中
    List<Employee> getEmpsByLastNameLike(String lastName);

    //封装一条数据到map中
    Map<String,Object> getEmpByIdReturnMap(Integer id);

    //封装多条数据到map中
    @MapKey("id")
    Map<Integer,Employee> getEmpsReturnMap(String lastName);
}
