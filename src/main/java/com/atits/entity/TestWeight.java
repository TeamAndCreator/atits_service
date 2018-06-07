package com.atits.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_test_weight")
public class TestWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String year;

    private String time;

    @OneToOne
    @JoinColumn
    private Role role;
    /*
    考评比例设置
     */
    private double nongWei; //农委
    private double expert; //外聘专家
    private double subChief_sta_lib; //副首席-岗位专家-综合试验站站长
    private double sta_lib; //岗位专家-综合试验站站长
    private double chief;//首席
    private double nongwei_expert;//农委-外聘专家
    private double chief_subChief;//首席-副首席

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNongWei() {
        return nongWei;
    }

    public void setNongWei(double nongWei) {
        this.nongWei = nongWei;
    }

    public double getExpert() {
        return expert;
    }

    public void setExpert(double expert) {
        this.expert = expert;
    }

    public double getSubChief_sta_lib() {
        return subChief_sta_lib;
    }

    public void setSubChief_sta_lib(double subChief_sta_lib) {
        this.subChief_sta_lib = subChief_sta_lib;
    }

    public double getSta_lib() {
        return sta_lib;
    }

    public void setSta_lib(double sta_lib) {
        this.sta_lib = sta_lib;
    }

    public double getChief() {
        return chief;
    }

    public void setChief(double chief) {
        this.chief = chief;
    }

    public double getNongwei_expert() {
        return nongwei_expert;
    }

    public void setNongwei_expert(double nongwei_expert) {
        this.nongwei_expert = nongwei_expert;
    }

    public double getChief_subChief() {
        return chief_subChief;
    }

    public void setChief_subChief(double chief_subChief) {
        this.chief_subChief = chief_subChief;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestWeight{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", time='" + time + '\'' +
                ", role=" + role +
                ", nongWei=" + nongWei +
                ", expert=" + expert +
                ", subChief_sta_lib=" + subChief_sta_lib +
                ", sta_lib=" + sta_lib +
                ", chief=" + chief +
                ", nongwei_expert=" + nongwei_expert +
                ", chief_subChief=" + chief_subChief +
                '}';
    }


}
