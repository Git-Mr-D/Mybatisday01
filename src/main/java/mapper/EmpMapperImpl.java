package mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.Emp;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public class EmpMapperImpl {
    SqlSession sqlSession =null;

    @Before
    public void start()throws Exception{
        SqlSessionFactoryBuilder sql=new SqlSessionFactoryBuilder();
        InputStream rs= Resources.getResourceAsStream("Mybatis.xml");
        SqlSessionFactory build = sql.build(rs);
          sqlSession = build.openSession();
    }

    @Test
    public void selectAll(){
        List<Emp> o=sqlSession.selectList("mapper.EmpMapperImpl.selectAll");
        o.forEach(temp->System.out.println(temp));
    }

    @Test
    public void selectById(){
        Emp emp= sqlSession.selectOne("mapper.EmpMapperImpl.selectById", 1012);
        System.out.println(emp);
    }

    @Test
    public void deleteById(){
        int i= sqlSession.delete("mapper.EmpMapperImpl.deleteById", 0);
        System.out.println("受影响行数:"+i);
    }

    @Test
    public void insertAll(){
        Emp emp=new Emp();
        emp.setEmpno(null);
        emp.setEname("麻花藤月");
        emp.setJob("总经理");
        emp.setMgr(1009);
        emp.setHiredate(new Date(new java.util.Date().getTime()));
        emp.setSal(66666.0);
        emp.setCOMM(6666.0);
        emp.setDeptno(10);
        int i= sqlSession.insert("mapper.EmpMapperImpl.insertAll", emp);
        System.out.println("受影响行数:"+i);
    }

    @Test
    public void updateById(){
        Emp emp=new Emp();
        emp.setEname("马腾");
        emp.setEmpno(1018);
        int i= sqlSession.update("mapper.EmpMapperImpl.updateById", emp);
        System.out.println("受影响行数:"+i);
    }

    @After
    public void end(){
        sqlSession.commit();
    }
}
