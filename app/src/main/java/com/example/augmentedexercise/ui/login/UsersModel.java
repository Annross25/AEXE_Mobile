package com.example.augmentedexercise.ui.login;

public class UsersModel {

    public String profPict,
            firstname,
            lastname,
            gender,
            birthday,
            age,
            email,
            password,
            address,
            number,
            userId,
            lastMessage,
            description,
            imageurl;

    public UsersModel(){

    }

    public UsersModel(String profPict, String firstname, String lastname, String gender,
                String birthday, String age, String email, String password, String address, String number,
                String userId, String lastMessage, String imageurl) {
        this.profPict = profPict;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.email = email;
        this.password = password;
        this.address = address;
        this.number = number;
        this.userId = userId;
        this.lastMessage = lastMessage;
        this.description = description;
        this.imageurl = imageurl;
    }

    public UsersModel(String firstname, String lastname, String gender, String email, String password) {
        this.firstname = firstname;
        this.firstname = lastname;
        this.gender = gender;
        this.email = email;
        this.password = password;
    }

    public String getProfPict() {
        return profPict;
    }

    public void setProfPict(String profPict) {
        this.profPict = profPict;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String username) {
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
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
}
