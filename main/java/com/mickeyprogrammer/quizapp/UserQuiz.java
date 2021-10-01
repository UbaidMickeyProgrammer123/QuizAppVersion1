package com.mickeyprogrammer.quizapp;

public class UserQuiz implements Comparable<UserQuiz> {
    String userName;
    int Islam;
    int PakStudy;
    int Java;

    @Override
    public String toString() {
        return "UserQuiz{" +
                "userName='" + userName + '\'' +
                ", Islam=" + Islam +
                ", PakStudy=" + PakStudy +
                ", Java=" + Java +
                ", Html=" + Html +
                ", GK=" + GK +
                ", Science=" + Science +
                ", totalMarks=" + totalMarks +
                ", position=" + position +
                '}';
    }

    int Html;
    int GK;
    int Science;
    int totalMarks;
    int position;

    public UserQuiz() {
        this.userName = "Nill";
        this.Islam = 0;
        this.PakStudy = 0;
        this.Java = 0;
        this.Html = 0;
        this.GK = 0;
        this.Science = 0;
        this.totalMarks = 0;
        this.position =0 ;

    }



    public UserQuiz(String userName, int Islam, int PakStudy, int Java, int Html, int GK, int Science, int totalMarks, int position) {
        this.userName = userName;
        this.Islam = Islam;
        this.PakStudy = PakStudy;
        this.Java = Java;
        this.Html = Html;
        this.GK = GK;
        this.Science = Science;
        this.totalMarks =Islam + PakStudy + Java + Html + GK + Science;
        this.position = position;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIslam() {
        return Islam;
    }

    public void setIslam(int islam) {
        this.Islam = islam;
    }

    public int getPakStudy() {
        return PakStudy;
    }

    public void setPakStudy(int pakStudy) {
        this.PakStudy = pakStudy;
    }

    public int getJava() {
        return Java;
    }

    public void setJava(int java) {
        this.Java = java;
    }

    public int getHtml() {
        return Html;
    }

    public void setHtml(int html) {
        this.Html = html;
    }

    public int getGK() {
        return GK;
    }

    public void setGK(int GK) {
        this.GK = GK;
    }

    public int getScience() {
        return Science;
    }

    public void setScience(int science) {
        this.Science = science;
    }

    public int getTotalMarks() {
        return Islam + PakStudy + Java + Html + GK + Science;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }


    @Override
    public int compareTo(UserQuiz userQuiz) {
        return this.position - userQuiz.position;
    }
}
