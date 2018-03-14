package com.mintlolly.dao.impl;

import com.mintlolly.dao.StudentQuantityEachCourseDAO;
import com.mintlolly.entity.StudentQuantityEachCourse;
import com.mintlolly.utils.HBaseUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create by jag on 2018/3/2
 */
@Service
public class StudentQuantityEachCourseImpl implements StudentQuantityEachCourseDAO {
    @Override
    public List<StudentQuantityEachCourse> query() {
        List<StudentQuantityEachCourse> list = new ArrayList<>();

        Map<String,Long> map = HBaseUtils.getInstance().query("number_div_course");

        for(Map.Entry<String, Long> entry: map.entrySet()) {
            StudentQuantityEachCourse model = new StudentQuantityEachCourse();
            model.setCourse(entry.getKey());
            model.setStudentQuantity(entry.getValue());

            list.add(model);
        }
        return list;
    }
}
