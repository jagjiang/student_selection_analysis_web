package com.mintlolly.dao.impl;

import com.mintlolly.dao.StudentQuantityEachAcademyDAO;
import com.mintlolly.entity.StudentQuantityEachAcademy;
import com.mintlolly.utils.HBaseUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create by jag on 2018/3/2
 */
@Service
public class StudentQuantityEachAcademyImpl implements StudentQuantityEachAcademyDAO {

    @Override
    public List<StudentQuantityEachAcademy> query() {
        List<StudentQuantityEachAcademy> list = new ArrayList();
        Map<String,Long> map = HBaseUtils.getInstance().query("number_div_academy");
        for(Map.Entry<String,Long> entity:map.entrySet()){
            StudentQuantityEachAcademy model = new StudentQuantityEachAcademy();
            model.setAcademy(entity.getKey());
            model.setStudentQuantity(entity.getValue());
            list.add(model);
        }
        return list;
    }
}
