package com.atits.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_test_manage")
public class TestManage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private TestStart testStart;

    @ManyToOne
    private User scoreUser;

    private double sum;//个人得分

    public TestManage(){}

    public TestManage(TestStart testStart, User user, double sum){
        this.testStart=testStart;
        this.scoreUser=user;
        this.sum=sum;
    }

    public TestManage(Integer id,String testStartYear,Integer testStartSystemId,String testStartSystemName
            ,String testStartDate,String testStartAddress,String scoreUserName,double sum){
        this.id=id;
        this.testStart=new TestStart();
        this.testStart.setYear(testStartYear);
        this.testStart.setSystem(new System(testStartSystemId,testStartSystemName));
        this.testStart.setDate(testStartDate);
        this.testStart.setAddress(testStartAddress);
        this.scoreUser=new User();
        this.scoreUser.setProfile(new Profile(scoreUserName));
        this.sum=sum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TestStart getTestStart() {
        return testStart;
    }

    public void setTestStart(TestStart testStart) {
        this.testStart = testStart;
    }

    public User getScoreUser() {
        return scoreUser;
    }

    public void setScoreUser(User scoreUser) {
        this.scoreUser = scoreUser;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "TestManage{" +
                "id=" + id +
                ", testStart=" + testStart +
                ", scoreUser=" + scoreUser +
                ", sum=" + sum +
                '}';
    }
}
