package com.ethan.dao;

import com.ethan.bean.Department;
import com.ethan.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeeDynamicSQLMapperTest {

    public static SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    @Test
    public void batchInsertEmpsByMysql() throws IOException {
        SqlSession sqlSession = getSqlSession();
        try {
            EmployeeDynamicSQLMapper mapper = sqlSession.getMapper(EmployeeDynamicSQLMapper.class);
            Employee emp1 = new Employee(null,"ek1","ek1@atguigu.com","0",new Department(1));
            Employee emp2 = new Employee(null,"ek2","ek2@atguigu.com","1",new Department(2));
            Employee emp3= new Employee(null,"ek3","ekc3@atguigu.com","0",new Department(1));
            ArrayList<Employee> employees = new ArrayList<>();
            employees.add(emp1);
            employees.add(emp2);
            employees.add(emp3);
            int count = mapper.batchInsertEmpsByMysql(employees);
            sqlSession.commit();
            System.out.println(count);
        }finally {
            sqlSession.close();
        }

    }
}