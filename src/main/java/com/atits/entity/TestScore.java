package com.atits.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_test_score")
public class TestScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn
    private TestStart testStart;

    private int A1;
    private int A2;
    private int A3;
    private int A4;
    private int A5;
    private int A6;
    private int sum;
    private String time; //打分时间

    @ManyToOne
    private User evaluationed;

    private int role;//被打分人身份，1、首席，2、副首席，3、岗位专家4、实验站站长

    @ManyToOne
    private User evaluation;

    private String testType;

    public TestScore(int A1,int A2,int A3,int A4,int A5,int A6){
        this.A1=A1;
        this.A2=A2;
        this.A3=A3;
        this.A4=A4;
        this.A5=A5;
        this.A6=A6;
    }

    public TestScore(){}

    public TestScore(TestStart testStart,User evaluationed,int role,User evaluation,String testType){
        this.testStart=testStart;
        this.evaluationed=evaluationed;
        this.role=role;
        this.evaluation=evaluation;
        this.testType=testType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestStart getTestStart() {
        return testStart;
    }

    public void setTestStart(TestStart testStart) {
        this.testStart = testStart;
    }

    public int getA1() {
        return A1;
    }

    public void setA1(int a1) {
        A1 = a1;
    }

    public int getA2() {
        return A2;
    }

    public void setA2(int a2) {
        A2 = a2;
    }

    public int getA3() {
        return A3;
    }

    public void setA3(int a3) {
        A3 = a3;
    }

    public int getA4() {
        return A4;
    }

    public void setA4(int a4) {
        A4 = a4;
    }

    public int getA5() {
        return A5;
    }

    public void setA5(int a5) {
        A5 = a5;
    }

    public int getA6() {
        return A6;
    }

    public void setA6(int a6) {
        A6 = a6;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getEvaluationed() {
        return evaluationed;
    }

    public void setEvaluationed(User evaluationed) {
        this.evaluationed = evaluationed;
    }

    public User getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(User evaluation) {
        this.evaluation = evaluation;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "TestScore{" +
                "id=" + id +
                ", testStart=" + testStart +
                ", A1=" + A1 +
                ", A2=" + A2 +
                ", A3=" + A3 +
                ", A4=" + A4 +
                ", A5=" + A5 +
                ", A6=" + A6 +
                ", sum=" + sum +
                ", time='" + time + '\'' +
                ", evaluationed=" + evaluationed +
                ", role=" + role +
                ", evaluation=" + evaluation +
                ", testType='" + testType + '\'' +
                '}';
    }
}
