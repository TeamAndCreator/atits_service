package com.atits.entity;


import javax.persistence.*;

/**
 * 实验站
 * @author zys
 */
@Entity
@Table(name = "t_station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String staName;


    @Column(length = 100000)
    private String content;

    private String company;

    @ManyToOne
    @JoinColumn( nullable = false)
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

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
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

    public Station(){}

    public Station(int id , String staName){
        this.id=id;
        this.staName=staName;
    }

    public Station(int id , String staName,String systemName,int state){
        this.id=id;
        this.staName=staName;
        this.state=state;
        this.system=new System();
        this.system.setSystemName(systemName);
    }
    public Station(int id,String staName, String content, String company,String systemName, String time,String date, int state) {
        this.id=id;
        this.staName = staName;
        this.content = content;
        this.company = company;
        this.system=new System();
        this.system.setSystemName(systemName);
        this.time = time;
        this.date=date;
        this.state = state;
    }
    public Station(int id,String staName, String company,String systemName, String time,String date, int state) {
        this.id=id;
        this.staName = staName;
        this.company = company;
        this.system=new System();
        this.system.setSystemName(systemName);
        this.time = time;
        this.date=date;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", staName='" + staName + '\'' +
                ", content='" + content + '\'' +
                ", company='" + company + '\'' +
                ", system=" + system +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", state=" + state +
                '}';
    }
}
