package com.studenti.studentiSpring.dto;


public class CourseDTO {


    private Long id;
    private String courseName;
    private String courseCode;
    private String courseDescription;
    private Long teacherId;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String courseName, String courseCode, String courseDescription, Long teacherId) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseDescription = courseDescription;
        this.teacherId = teacherId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
