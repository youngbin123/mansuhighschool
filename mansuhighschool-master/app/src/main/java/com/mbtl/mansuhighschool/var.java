package com.mbtl.mansuhighschool;

import android.app.Application;

public class var extends Application {
    private String grade;
    private String classroom;

    public String getgrade() {
        return grade;
    }
    public String getclassroom() {
        return classroom;
    }


    public void setgrade( String grade ) {
        this.grade = grade;
    }

    public void setclassroom( String classsroom ) {
        this.classroom = classsroom;
    }
}
