package com.atits.entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_test_start")
public class TestStart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String year;// 考评年度
    private String date;//考评日期
    private String address;//考评地点

    @ManyToMany
    private Set<User> users=new HashSet<>();//考评人员

    //权重设置
    //-----------------考评人员to被考评人员
    //--首席
    private double a;//体系办to首席
    private double b;//副首席、研究室主任、实验站站长to首席
    private double c;//外聘专家to首席
    //副首席
    private double d;//体系办、外聘专家to副首席
    private double e;//首席to副首席
    private double f;//研究室主任、实验站站长to副首席
    //研究室主任、实验站站长
    private double g;//首席、副首席to研究室主任、实验站站长
    private double h;//研究室主任、实验站站长to研究室主任、实验站站长

    public TestStart() {
        users.add(new User());
    }

    public TestStart(Set<User> users) {
        this.users = users;
    }

    private int state;//考评状态 ：1、启动考评，2、考评开始，3、考评结束


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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "TestStart{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", date='" + date + '\'' +
                ", address='" + address + '\'' +
                ", users=" + users +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f=" + f +
                ", g=" + g +
                ", h=" + h +
                ", state=" + state +
                '}';
    }
}
