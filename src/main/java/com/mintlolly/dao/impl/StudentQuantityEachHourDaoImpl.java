package com.mintlolly.dao.impl;

import com.mintlolly.dao.StudentQuantityEachHourDAO;
import com.mintlolly.entity.StudentQuantityEachHour;
import com.mintlolly.utils.HBaseUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Create by jag on 2018/2/27
 */
@Service
public class StudentQuantityEachHourDaoImpl implements StudentQuantityEachHourDAO {

    @Override
    public List<StudentQuantityEachHour> query(String day) {
        List<StudentQuantityEachHour> list = new ArrayList<>();


        // 去HBase表中根据day获取实战课程对应的访问量
        Map<String, Long> map = HBaseUtils.getInstance().query("number_div_time",day);
        for(Map.Entry<String, Long> entry: map.entrySet()) {
            StudentQuantityEachHour model = new StudentQuantityEachHour();
            model.setHour(entry.getKey());
            model.setStudentQuantity(entry.getValue());

            list.add(model);
        }

        return list;
    }
}
