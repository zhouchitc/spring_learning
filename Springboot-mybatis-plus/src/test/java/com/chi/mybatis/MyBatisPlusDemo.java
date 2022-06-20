package com.chi.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chi.mybatis.domain.Student;
import com.chi.mybatis.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author chi
 * @Description: TODO
 * @date 2022/6/20 09:33
 * @Version 1.0
 */
@SpringBootTest
public class MyBatisPlusDemo {

    @Autowired
    private StudentMapper studentMapper;

    /**
     *
     * @Description //TODO select * from t_student
     */
    @Test
    public void selectStudentList() {
        List<Student> students = studentMapper.selectList(null);
        students.forEach(System.out::println);
    }

    /**
     *
     * @Description //TODO select * from t_student where name like "%a%" and score >= 60;
     */
    @Test
    public void selectStudentList2() {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.like("name", "a").lt("score", 60);
        List<Student> students = studentMapper.selectList(wrapper);
        students.forEach(System.out::println);
    }
}
