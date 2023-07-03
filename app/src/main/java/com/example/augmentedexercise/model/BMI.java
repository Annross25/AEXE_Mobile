package com.example.augmentedexercise.model;

public class BMI {

    public String id, age, weight, feet, inch, bmiResult, progress;

    public BMI(){
    }

    public BMI(String id, String age, String weight, String feet, String inch, String progress, String bmiResult) {
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.feet = feet;
        this.inch = inch;
        this.progress = progress;
        this.bmiResult = bmiResult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFeet() {
        return feet;
    }

    public void setFeet(String feet) {
        this.feet = feet;
    }

    public String getInch() {
        return inch;
    }

    public void setInch(String inch) {
        this.inch = inch;
    }

    public String getBmiResult() {
        return bmiResult;
    }

    public void setBmiResult(String bmiResult) {
        this.bmiResult = bmiResult;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
