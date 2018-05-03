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

    @ManyToOne
    @JoinColumn
    private System system;

    private String date;//考评日期

    private int state;//考评状态：0是未考评，1是已考评

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TestManage{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", examiner=" + examiner +
                ", examedner=" + examedner +
                ", system=" + system +
                ", date='" + date + '\'' +
                ", state=" + state +
                '}';
    }
}
