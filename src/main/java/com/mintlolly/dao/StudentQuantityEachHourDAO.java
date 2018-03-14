package com.mintlolly.dao;

import com.mintlolly.entity.StudentQuantityEachHour;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by jag on 2018/2/27
 */
public interface StudentQuantityEachHourDAO {
    public List<StudentQuantityEachHour> query(String day);
}
