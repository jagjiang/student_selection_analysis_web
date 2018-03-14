package com.mintlolly.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Create by jag on 2018/3/2
 */
@Data
public class AcademyAndCourse {
    private String academyName;
    private Long studentQuantity;
    private List<StudentQuantityEachCourse> courseMap;
}
