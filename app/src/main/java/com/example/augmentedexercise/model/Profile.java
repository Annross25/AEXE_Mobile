package com.example.augmentedexercise.model;

public class Profile {

    public String id, profile_url, email, password, first_name, last_name, gender, birthday, age,
            address, mobile_number, role, createdAt, regPass;

    public Profile(){
    }

    public Profile(String id, String profile_url, String email, String password, String first_name,
                   String last_name, String gender, String birthday, String age, String address,
                   String mobile_number, String role, String createdAt, String regPass){
        this.id = id;
        this.profile_url = profile_url;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birthday= birthday;
        this.age = age;
        this.address = address;
        this.mobile_number = mobile_number;
        this.role = role;
        this.createdAt = createdAt;
        this.regPass = regPass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createAt) {
        this.createdAt = createdAt;
    }

    public String getRegPass() {
        return regPass;
    }
    public void setRegPass(String regPass) {
        this.regPass = regPass;
    }
}
