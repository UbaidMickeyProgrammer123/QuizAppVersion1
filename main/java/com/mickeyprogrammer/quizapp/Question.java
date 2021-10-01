package com.mickeyprogrammer.quizapp;

public class Question {
    int id;
    String question,option1,option2,option3,option4;
    int image,correctAns;
    public Question(int id, String question, String option1, String option2, String option3, String option4, int image, int correctAns) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.image = image;
        this.correctAns = correctAns;
    }

    public Question() {
        this.id = 0;
        this.question = "0";
        this.option1 = "0";
        this.option2 = "0";
        this.option3 = "0";
        this.option4 = "0";
        this.image = 0;
        this.correctAns = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }


}
