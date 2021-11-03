package com.ethan.dao;

import com.ethan.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class EmployeeMapperTest {

    public static SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //1.根据mybatis-config.xml创建一个sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        /*
        * 2.获取sqlSession实例，能直接执行已经映射的sql语句
        * 默认获取的sqlSession=sqlSessionFactory.openSession(false),自动提交事务是关闭的
        * */
        return sqlSessionFactory.openSession();
    }

    @Test
    public void testCRUD() throws IOException {
        //注意默认的SqlSession是没有自动提交事务的，需要手动提交事务

        SqlSession sqlSession = null;
        try{
            sqlSession = getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //1.查询
            Employee emp = mapper.getEmpById(1);
            System.out.println(emp);
            //2.增加插入数据需要手动提交事务
          /*  Employee employee = new Employee(null,"insert","insert@atguigu.com","0");
            mapper.addEmp(employee);
            System.out.println(employee);
            System.out.println(employee.getId());*/

            //3.修改更新数据
           /* Employee employee = new Employee(4,"ethan1","ethanUpdate@atguigu.com","1");
            mapper.updateEmp(employee);*/

            //4.删除
            //mapper.deleteEmpById(4);

            //手动提交事务
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 测试dao层接口的方法的传参的理解
     */
    @Test
    public void test02() throws IOException {
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee tom = mapper.getEmpByIdAndLastName(1, "tom");
            System.out.println(tom);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 测试select的返回数据如何封装
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getEmpsByLastNameLike("%n%");
            for (Employee emp : emps) {
                System.out.println(emp);
            }
            System.out.println("============================");
            Map<String, Object> returnMap = mapper.getEmpByIdReturnMap(5);
            System.out.println(returnMap);
            System.out.println("============================");
            Map<Integer, Employee> employeesMap = mapper.getEmpsReturnMap("%n%");
            System.out.println(employeesMap);

        }finally {
            sqlSession.close();
        }
    }


    /**
     * 学习resultMap结果集映射的高级用法
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee emp = mapper.getEmpById(1);
            System.out.println(emp);
            System.out.println("=============联合查询1===============");
            Employee empAndDept = mapper.getEmpAndDeptById(9);
            System.out.println(empAndDept);
            System.out.println("=============联合查询2:分布查询===============");
            Employee empByIdStep = mapper.getEmpByIdStep(9);
            System.out.println(empByIdStep.getLastName());
            System.out.println(empByIdStep.getDept());
        }finally {
            sqlSession.close();
        }
    }

}