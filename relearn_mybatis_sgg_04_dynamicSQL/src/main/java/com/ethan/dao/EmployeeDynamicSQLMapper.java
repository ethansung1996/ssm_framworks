package com.ethan.dao;

import com.ethan.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDynamicSQLMapper {
    /**
     * mysql下的批量插入的两种实现方式
     * 注解@Param("employeeList")很关键，用于映射foreach标签中的collecion
     * @param employeeList
     * @return
     */
    public int batchInsertEmpsByMysql(@Param("employeeList") List<Employee> employeeList);

    /**
     * oracle下的批量插入的两种实现方式
     * @param employeeList
     * @return
     */
    public int batchInsertEmpsByOracle(@Param("employeeList") List<Employee> employeeList);

}
