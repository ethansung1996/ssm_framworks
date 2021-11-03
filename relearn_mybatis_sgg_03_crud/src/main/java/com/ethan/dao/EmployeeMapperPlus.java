package com.ethan.dao;

import com.ethan.bean.Employee;

public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);
    //联合查询，一个pojo类的成员变量是引用类型的，而不是基本数据类型
    Employee getEmpAndDeptById(Integer id);

    Employee getEmpByIdStep(Integer id);
}
