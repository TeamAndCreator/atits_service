package com.atits.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "t_test_manage")
public class TestManage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String year;//考评年度

    @OneToOne
    @JoinColumn
    private User examiner;//评分人

    @OneToOne
    @JoinColumn
    private User examedner; //被评分人

    private String examinerOfJob;//评分人的职位

    private String examednerOfJob; //被评分人的职位

    @ManyToOne
    @JoinColumn
    private System system;

    private String date;//考评日期

    private int mstate;//考评状态：0是未考评，1是已考评

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public User getExaminer() {
        return examiner;
    }

    public void setExaminer(User examiner) {
        this.examiner = examiner;
    }

    public User getExamedner() {
        return examedner;
    }

    public void setExamedner(User examedner) {
        this.examedner = examedner;
    }

    public String getExaminerOfJob() {
        return examinerOfJob;
    }

    public void setExaminerOfJob(String examinerOfJob) {
        this.examinerOfJob = examinerOfJob;
    }

    public String getExamednerOfJob() {
        return examednerOfJob;
    }

    public void setExamednerOfJob(String examednerOfJob) {
        this.examednerOfJob = examednerOfJob;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMstate() {
        return mstate;
    }

    public void setMstate(int mstate) {
        this.mstate = mstate;
    }

    @Override
    public String toString() {
        return "TestManage{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", examiner=" + examiner +
                ", examedner=" + examedner +
                ", examinerOfJob='" + examinerOfJob + '\'' +
                ", examednerOfJob='" + examednerOfJob + '\'' +
                ", system=" + system +
                ", date='" + date + '\'' +
                ", mstate=" + mstate +
                '}';
    }
}
