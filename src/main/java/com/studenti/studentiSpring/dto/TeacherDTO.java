package com.studenti.studentiSpring.dto;




public class TeacherDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;

    public TeacherDTO() {
    }

    public TeacherDTO(Long id,String firstName, String lastName, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.id = id;
    }

    public TeacherDTO(String firstName, String lastName, String email, Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
