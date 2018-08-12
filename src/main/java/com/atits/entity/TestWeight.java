package com.atits.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_test_weight")
public class TestWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //权重设置
    //-----------------考评人员to被考评人员
    //--首席
    private double a;//体系办to首席 40
    private double b;//副首席、岗位专家、实验站站长to首席 30
    private double c;//外聘专家to首席 30
    //副首席
    private double d;//体系办、外聘专家to副首席 40
    private double e;//首席to副首席 30
    private double f;//岗位专家、实验站站长to副首席 30
    //研究室主任、实验站站长
    private double g;//首席、副首席to岗位专家、实验站站长 60
    private double h;//岗位专家、实验站站长to岗位专家、实验站站长 40

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "TestWeight{" +
                "id=" + id +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f=" + f +
                ", g=" + g +
                ", h=" + h +
                '}';
    }
}
