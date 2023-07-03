package com.example.augmentedexercise.model;

public class Progress {

    String id;
    int fullbody_progress,
        abs_progress,
        chest_progress,
        leg_progress,
        shoulder_progress;

    public Progress(){}

    public Progress(String id, int fullbody_progress, int abs_progress, int chest_progress, int leg_progress, int shoulder_progress) {
        this.id = id;
        this.fullbody_progress = fullbody_progress;
        this.abs_progress = abs_progress;
        this.chest_progress = chest_progress;
        this.leg_progress = leg_progress;
        this.shoulder_progress = shoulder_progress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFullbody_progress() {
        return fullbody_progress;
    }

    public void setFullbody_progress(int fullbody_progress) {
        this.fullbody_progress = fullbody_progress;
    }

    public int getAbs_progress() {
        return abs_progress;
    }

    public void setAbs_progress(int abs_progress) {
        this.abs_progress = abs_progress;
    }

    public int getChest_progress() {
        return chest_progress;
    }

    public void setChest_progress(int chest_progress) {
        this.chest_progress = chest_progress;
    }

    public int getLeg_progress() {
        return leg_progress;
    }

    public void setLeg_progress(int leg_progress) {
        this.leg_progress = leg_progress;
    }

    public int getShoulder_progress() {
        return shoulder_progress;
    }

    public void setShoulder_progress(int shoulder_progress) {
        this.shoulder_progress = shoulder_progress;
    }
}
