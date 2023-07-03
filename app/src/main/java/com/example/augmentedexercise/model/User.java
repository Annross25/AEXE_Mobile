package com.example.augmentedexercise.model;

public class User {

    public String profPict, first_name, last_name, gender, birthday, age, email, password,
            address, mobile_number, userId, lastMessage, description, imageurl;

    public User(){

    }

    public User(String profPict, String first_name, String last_name, String gender,
                String birthday, String age, String email, String password, String address, String mobile_number,
                String userId, String lastMessage, String imageurl) {
        this.profPict = profPict;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.email = email;
        this.password = password;
        this.address = address;
        this.mobile_number = mobile_number;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.description = description;
        this.imageurl = imageurl;
    }

    public String getProfPict() {
        return profPict;
    }

    public void setProfPict(String profPict) {
        this.profPict = profPict;
    }

    public String getFirst_name(){
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return mobile_number;
    }

    public void setNumber(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
