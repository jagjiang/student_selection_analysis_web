package com.mintlolly.controller;

import com.mintlolly.dao.StudentQuantityEachAcademyDAO;
import com.mintlolly.dao.StudentQuantityEachCourseDAO;
import com.mintlolly.dao.StudentQuantityEachHourDAO;
import com.mintlolly.entity.AcademyAndCourse;
import com.mintlolly.entity.StudentQuantityEachAcademy;
import com.mintlolly.entity.StudentQuantityEachCourse;
import com.mintlolly.entity.StudentQuantityEachHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Create by jag on 2018/2/27
 */
@RestController
@RequestMapping("/data")
public class APPController {

    @Autowired
    StudentQuantityEachHourDAO studentQuantityEachHourDAO;
    @Autowired
    StudentQuantityEachAcademyDAO studentQuantityEachAcademyDAO;
    @Autowired
    StudentQuantityEachCourseDAO studentQuantityEachCourseDAO;

    private static Map<String, List<String>> courses = new HashMap<>();
    static {
        courses.put("经济管理学院", Arrays.asList("市场营销","国际经济与贸易","工商管理"));
        courses.put("文法学院",Arrays.asList("法学","新闻学","汉语文言学"));
        courses.put("外国语学院",Arrays.asList("英语基础","法语基础","商务英语"));
        courses.put("信息科学与工程学院",Arrays.asList("物联网","大数据","人工智能"));
        courses.put("艺术学院",Arrays.asList("声乐鉴赏","舞蹈","架子鼓"));
        courses.put("电气工程学院",Arrays.asList("数字电路","模拟电路","自动化"));
        courses.put("纺织服装学院",Arrays.asList("纺织结构复合材料及其制备","纺织品清洁生产","信息化服装制造"));
        courses.put("机械工程学院",Arrays.asList("3D增材制造","车辆工程","机械基础"));
    }

    @RequestMapping(value = "/number_div_time", method = RequestMethod.POST)
    @ResponseBody
    public List<StudentQuantityEachHour> courseClickCount() throws Exception {
        long midnightStudentQuantity = 0;
        long morningStudentQuantity = 0;
        long afternoonStudentQuantity = 0;
        long eveningStudentQuantity = 0;
        List<StudentQuantityEachHour> list = studentQuantityEachHourDAO.query("20180301");
        List<StudentQuantityEachHour> result = new ArrayList<>();
        for(StudentQuantityEachHour model : list) {
            Integer hour = Integer.valueOf(model.getHour().substring(8,10));
            if (hour>0 && hour<=6){
                midnightStudentQuantity += model.getStudentQuantity();
            } else if (hour>6 && hour<=12){
                morningStudentQuantity += model.getStudentQuantity();
            }else if (hour>12 && hour<=18){
                afternoonStudentQuantity += model.getStudentQuantity();
            }else{
                eveningStudentQuantity  += model.getStudentQuantity();
            }

        }
        result.add(new StudentQuantityEachHour("0-6",midnightStudentQuantity));
        result.add(new StudentQuantityEachHour("6-12",morningStudentQuantity));
        result.add(new StudentQuantityEachHour("12-18",afternoonStudentQuantity));
        result.add(new StudentQuantityEachHour("18-24",eveningStudentQuantity));

        return result;
    }

    @RequestMapping(value = "/number_div_academy_course", method = RequestMethod.POST)
    @ResponseBody
    public List<AcademyAndCourse> academyAndCourse() throws Exception {
        List<AcademyAndCourse> list = new ArrayList<>();
        List<StudentQuantityEachAcademy> academies =studentQuantityEachAcademyDAO.query();
        List<StudentQuantityEachCourse> courseList =studentQuantityEachCourseDAO.query();

        for (StudentQuantityEachAcademy academy:academies) {
            AcademyAndCourse academyAndCourse = new AcademyAndCourse();
            List<StudentQuantityEachCourse> academyCourseList = new ArrayList<>();
            academyAndCourse.setAcademyName(academy.getAcademy());
            academyAndCourse.setStudentQuantity(academy.getStudentQuantity());
            for (StudentQuantityEachCourse course:courseList){
                if(courses.get(academy.getAcademy()).contains(course.getCourse())){
                    StudentQuantityEachCourse studentQuantityEachCourse = new StudentQuantityEachCourse();
                    studentQuantityEachCourse.setCourse(course.getCourse());
                    studentQuantityEachCourse.setStudentQuantity(course.getStudentQuantity());
                    academyCourseList.add(studentQuantityEachCourse);
                }
            }
            academyAndCourse.setCourseMap(academyCourseList);
            list.add(academyAndCourse);
        }

        return list;
    }

}
