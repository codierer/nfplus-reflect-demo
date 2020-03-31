package com.nfplus.m;

import java.util.Date;

//表名转换为类名
public class StudentAssess {


//列名转换为成员变量，以及相应get set方法

    //
    private int score;
    //
    private String studentName;
    //
    private String courseName;
    //
    private int id;
    //
    private String schoolName;


    public int getScore(){

        return score;

    }
    public void setScore (int score){

        this.score = score;

    }
    public String getStudentName(){

        return studentName;

    }
    public void setStudentName (String studentName){

        this.studentName = studentName;

    }
    public String getCourseName(){

        return courseName;

    }
    public void setCourseName (String courseName){

        this.courseName = courseName;

    }
    public int getId(){

        return id;

    }
    public void setId (int id){

        this.id = id;

    }
    public String getSchoolName(){

        return schoolName;

    }
    public void setSchoolName (String schoolName){

        this.schoolName = schoolName;

    }


}