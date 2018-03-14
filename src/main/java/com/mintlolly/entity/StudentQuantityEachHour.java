package com.mintlolly.entity;


import lombok.Data;


/**
 * Create by jag on 2018/2/27
 */
@Data
public class StudentQuantityEachHour {
    private String hour;
    private Long studentQuantity;
    public StudentQuantityEachHour(){

    }
    public StudentQuantityEachHour(String hour,Long studentQuantity){
        this.hour = hour;
        this.studentQuantity = studentQuantity;
    }
}
