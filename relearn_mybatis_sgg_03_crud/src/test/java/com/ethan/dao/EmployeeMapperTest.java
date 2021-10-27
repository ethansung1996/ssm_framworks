package com.ethan.dao;

import com.ethan.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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
           /* Employee emp = mapper.getEmpById(1);
            System.out.println(emp);*/
            //2.增加插入数据需要手动提交事务
            Employee employee = new Employee(null,"insert","insert@atguigu.com","0");
            mapper.addEmp(employee);
            System.out.println(employee);
            System.out.println(employee.getId());

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

}