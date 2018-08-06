package com.atits.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 研究室
 *
 * @author zys
 */
@Entity
@Table(name = "t_laboratory")
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String labName;

    @Column(length = 100000)
    private String content;

    private String company;

    @ManyToOne
    @JoinColumn(nullable = false)
    private System system;

    private String time;

    private String date;

    private int state;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Laboratory(){}

    public Laboratory(int id ,String labName){
        this.id=id;
        this.labName=labName;
    }

    public Laboratory(int id ,String labName,String systemName,int state){
        this.id=id;
        this.labName=labName;
        this.state=state;
        this.system=new System();
        this.system.setSystemName(systemName);
    }

    public Laboratory(int id,String labName, String content, String company,String systemName, String time, String date, int state) {
        this.id=id;
        this.labName = labName;
        this.content = content;
        this.company = company;
        this.system=new System();
        this.system.setSystemName(systemName);
        this.time = time;
        this.date = date;
        this.state = state;
    }

    public Laboratory(int id,String labName, String company,String systemName, String time, String date, int state) {
        this.id=id;
        this.labName = labName;
        this.company = company;
        this.system=new System();
        this.system.setSystemName(systemName);
        this.time = time;
        this.date = date;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "id=" + id +
                ", labName='" + labName + '\'' +
                ", content='" + content + '\'' +
                ", company='" + company + '\'' +
                ", system=" + system +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", state=" + state +
                '}';
    }
}
