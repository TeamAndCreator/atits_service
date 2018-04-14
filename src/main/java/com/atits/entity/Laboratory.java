package com.atits.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 研究室
 *
 * @author zys
 */
@Entity(name = "t_laboratory")
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

    private int state;

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

    @Override
    public String toString() {
        return "Laboratory{" +
                "id=" + id +
                ", labName='" + labName + '\'' +
                ", content='" + content + '\'' +
                ", company='" + company + '\'' +
                ", system=" + system +
                ", time='" + time + '\'' +
                ", state=" + state +
                '}';
    }
}
